package pe.com.project1.ms.infraestructure.gateway;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import pe.com.project1.ms.application.gateway.BankAccountBalanceManagementService;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountBalance;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BankAccountBalanceManagementMicroserviceClient implements BankAccountBalanceManagementService {
    private final WebClient webClient;

    @Override
    public Mono<BankAccountBalance> postBankAccountBalanceManagement(BankAccount bankAccount) {
    	BankAccountBalance bankAccountBalance = new BankAccountBalance();
    	bankAccountBalance.setBalance(BigDecimal.ZERO);
    	bankAccountBalance.setBankAccountId(bankAccount.getId());
    	bankAccountBalance.setTransactionCountLimit(10);
        return webClient.post()
                .uri("/bank-account-balance")
                .body(Mono.just(bankAccountBalance), BankAccountBalance.class)
                .retrieve()
                .bodyToMono(BankAccountBalance.class);
    }

}
