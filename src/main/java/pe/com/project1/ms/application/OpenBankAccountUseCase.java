package pe.com.project1.ms.application;

import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.infraestructure.rest.request.OpenAccountRequest;
import reactor.core.publisher.Mono;

public interface OpenBankAccountUseCase {
    Mono<BankAccount> openBankAccount(OpenAccountRequest openAccountRequest);
}
