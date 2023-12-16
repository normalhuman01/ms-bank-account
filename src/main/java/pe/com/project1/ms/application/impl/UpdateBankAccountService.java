package pe.com.project1.ms.application.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pe.com.project1.ms.application.UpdateBankAccountUseCase;
import pe.com.project1.ms.application.persistence.BankAccountRepository;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateBankAccountService implements UpdateBankAccountUseCase {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccount> updateBankAccountState(String bankAccountNumber, UpdateStateAccountRequest updateStateAccountRequest) {
        log.debug("UpdateStateAccount {}", updateStateAccountRequest);
        return bankAccountRepository.updateBankAccountState(bankAccountNumber,
                updateStateAccountRequest.getProductState());
    }
}
