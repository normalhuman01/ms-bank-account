package pe.com.project1.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project1.ms.application.OpenBankAccountUseCase;
import pe.com.project1.ms.application.gateway.BankAccountBalanceManagementService;
import pe.com.project1.ms.application.gateway.CustomersProductsOverviewService;
import pe.com.project1.ms.application.persistence.BankAccountRepository;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountBalance;
import pe.com.project1.ms.domain.account.BankAccountTerms;
import pe.com.project1.ms.domain.account.BankAccountType;
import pe.com.project1.ms.domain.product.Product;
import pe.com.project1.ms.domain.product.ProductState;
import pe.com.project1.ms.infraestructure.rest.request.OpenAccountRequest;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OpenBankAccountService implements OpenBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;
    private final CustomersProductsOverviewService customersProductsOverviewService;
    private final BankAccountBalanceManagementService bankAccountBalanceManagement;
    
    @Override
    public Mono<BankAccount> openBankAccount(OpenAccountRequest openAccountRequest) {
    	BankAccount bankAccount = this.mapOpenAccountRequestToBankAccount(openAccountRequest);
        String accountHolderId = openAccountRequest.getAccountHolderId();
        Mono<BankAccount> bankAccountCreated = Mono.when(this.assertThatCustomerDosentHaveDebt(accountHolderId), this.canOpenBankAccount(openAccountRequest))
                .then(Mono.just(bankAccount));
        
        Mono<Product> productMono = bankAccountCreated.flatMap(customersProductsOverviewService::postCustomersProductsOverview);
        Mono<BankAccountBalance> bankAccountBalanceMono = bankAccountCreated.flatMap(bankAccountBalanceManagement::postBankAccountBalanceManagement);

        return Mono.when(productMono, bankAccountBalanceMono)
        		.then(bankAccountRepository.save(bankAccount));
    }

    private Mono<Boolean> assertThatCustomerDosentHaveDebt(String accountHolderId) {
        log.debug(accountHolderId);
        Mono<Boolean> hasDebtMono = Mono.just(false);
        return hasDebtMono.flatMap(hastDebt -> {
            if (hastDebt) {
                return Mono.error(new RuntimeException("El cliente tiene una deuda pendiente!"));
            }
            return Mono.just(hastDebt);
        });
    }

    private Mono<Boolean> assertThatPersonalCustomerDosentHaveBankAccount(OpenAccountRequest openAccountRequest) {
        String customerId = openAccountRequest.getAccountHolderId();
        BankAccountType bankAccounType = openAccountRequest.getBankAccountType();
        return bankAccountRepository.existByAccountHolderIdAndAccountType(customerId, bankAccounType)
                .flatMap(hasAccount -> {
                    if (hasAccount) {
                        log.debug("El cliente ya tiene una cuenta de este tipo");
                        return Mono.error(new RuntimeException("El cliente ya tiene una cuenta de este tipo"));
                    }
                    return Mono.just(hasAccount);
                });
    }

    private Mono<Boolean> canOpenBankAccount(OpenAccountRequest openAccountRequest) {
        switch (openAccountRequest.getCustomerType()) {
            case ENTERPRISE:
                if (!openAccountRequest.getBankAccountType().equals(BankAccountType.CURRENT)) {
                    return Mono.error(new RuntimeException("Clientes Empresariales solo pueden abrir cuentas corrientes"));
                }
                return Mono.just(true);
            case ENTERPRISE_PYME:
                if (!openAccountRequest.getBankAccountType().equals(BankAccountType.CURRENT)) {
                    return Mono.error(new RuntimeException("Clientes Empresariales solo pueden abrir cuentas corrientes"));
                }
                return this.assertThatCustomerHasCreditCard(openAccountRequest.getAccountHolderId());
            case PERSONAL:
                return this.assertThatPersonalCustomerDosentHaveBankAccount(openAccountRequest);
            case PERSONAL_VIP:
                Mono<Boolean> hasAccountMono = this.assertThatPersonalCustomerDosentHaveBankAccount(openAccountRequest);
                Mono<Boolean> hasCreditCardMono = this.assertThatCustomerHasCreditCard(openAccountRequest.getAccountHolderId());
                return Mono.when(hasAccountMono, hasCreditCardMono)
                        .then(Mono.just(true));
            default:
                return Mono.error(new RuntimeException("Tipo de cliente no soportado!!"));
        }
    }

    private Mono<Boolean> assertThatCustomerHasCreditCard(String customerId) {
        log.debug(customerId);
        Mono<Boolean> hasCreditCardMono = Mono.just(true);
        return hasCreditCardMono.flatMap(hasCreditCard -> {
            if (!hasCreditCard) {
                return Mono.error(new RuntimeException("Cliente no tiene una tarjeta de credito"));
            }
            return Mono.just(hasCreditCard);
        });
    }

    private BankAccount mapOpenAccountRequestToBankAccount(OpenAccountRequest openAccountRequest) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setName("Cuenta Bancaria");
        bankAccount.setCustomerId(openAccountRequest.getAccountHolderId());
        bankAccount.setBankAccountNumber("123456678");
        bankAccount.setBalance(BigDecimal.ZERO);
        bankAccount.setBankAccountType(openAccountRequest.getBankAccountType());
        bankAccount.setBankAccountTerms(new BankAccountTerms());
        bankAccount.setProductState(ProductState.ACTIVE);
        bankAccount.setCreatedAt(LocalDateTime.now());
        return bankAccount;
    }

}
