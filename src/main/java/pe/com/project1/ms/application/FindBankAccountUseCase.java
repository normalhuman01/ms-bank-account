package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FindBankAccountUseCase {

    Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber);

    Flux<BankAccount> findByAccountHolderId(String accountHolderId);

    Mono<BankAccount> findById(String id);

    Flux<BankAccount> findAll();

    Flux<BankAccount> findByBankAccountType(BankAccountType bankAccountType);
}
