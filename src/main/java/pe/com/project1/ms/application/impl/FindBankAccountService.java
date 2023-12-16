package pe.com.project1.ms.application.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.project1.ms.application.FindBankAccountUseCase;
import pe.com.project1.ms.application.persistence.BankAccountRepository;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FindBankAccountService implements FindBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
        return bankAccountRepository.findByBankAccountNumber(bankAccountNumber);
    }

    @Override
    public Flux<BankAccount> findByAccountHolderId(String accountHolderId) {
        return bankAccountRepository.findByAccountHolderId(accountHolderId);
    }

    @Override
    public Mono<BankAccount> findById(String id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Flux<BankAccount> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Flux<BankAccount> findByBankAccountType(BankAccountType bankAccountType) {
        return bankAccountRepository.findByBankAccountType(bankAccountType);
    }

}
