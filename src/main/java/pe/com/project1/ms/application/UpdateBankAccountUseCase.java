package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.publisher.Mono;

public interface UpdateBankAccountUseCase {

    Mono<BankAccount> updateBankAccountState(String bankAccountNumber,
                                             UpdateStateAccountRequest updateStateAccountRequest);

}
