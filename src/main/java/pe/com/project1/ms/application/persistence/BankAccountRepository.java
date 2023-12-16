package pe.com.project1.ms.application.persistence;

import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountType;
import pe.com.project1.ms.domain.product.ProductState;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountRepository {
    Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

    Mono<BankAccount> save(BankAccount bankAccount);

    Flux<BankAccount> findByAccountHolderId(String accountHolderId);

    Mono<BankAccount> updateBankAccountState(String bankAccountNumber, ProductState productState);

    Flux<BankAccount> findAll();

    Flux<BankAccount> findByBankAccountType(BankAccountType bankAccountType);

    Mono<BankAccount> findById(String id);

    Mono<Boolean> existByAccountHolderIdAndAccountType(String customerId, BankAccountType bankAccounType);

}
