package pe.com.project1.ms.infraestructure.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import pe.com.project1.ms.application.FindBankAccountUseCase;
import pe.com.project1.ms.application.OpenBankAccountUseCase;
import pe.com.project1.ms.application.UpdateBankAccountUseCase;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.infraestructure.rest.request.OpenAccountRequest;
import pe.com.project1.ms.infraestructure.rest.request.UpdateStateAccountRequest;
import reactor.core.CorePublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BankAccountHandler {

    private final OpenBankAccountUseCase openBankAccountUseCase;
    private final FindBankAccountUseCase findBankAccountUseCase;
    private final UpdateBankAccountUseCase updateBankAccountUseCase;

    public Mono<ServerResponse> postBankAccount(ServerRequest request) {
        return request.bodyToMono(OpenAccountRequest.class)
                .map(openBankAccountUseCase::openBankAccount)
                .flatMap(bankAccount -> this.toServerResponse(bankAccount, HttpStatus.CREATED));
    }

    public Mono<ServerResponse> getBankAccountById(ServerRequest request) {
        Mono<BankAccount> bankAccountMono = findBankAccountUseCase.findById(request.pathVariable("id"));
        return this.toServerResponse(bankAccountMono, HttpStatus.OK);
    }

    public Mono<ServerResponse> getAllBankAccountsByAccountHolderId(ServerRequest request) {
        Flux<BankAccount> bankAccountFlux = request.queryParam("accountHolderId")
                .map(findBankAccountUseCase::findByAccountHolderId)
                .orElseGet(Flux::empty);
        return this.toServerResponse(bankAccountFlux, HttpStatus.OK);
    }

    public Mono<ServerResponse> patchBankAccountState(ServerRequest request) {
        return request.bodyToMono(UpdateStateAccountRequest.class)
                .map(updateStateAccountRequest -> updateBankAccountUseCase.updateBankAccountState(request.pathVariable("id"), updateStateAccountRequest))
                .flatMap(bankAccount -> this.toServerResponse(bankAccount, HttpStatus.OK));
    }

    private Mono<ServerResponse> toServerResponse(CorePublisher<BankAccount> bankAccount, HttpStatus status) {
        return ServerResponse
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(bankAccount, BankAccount.class);
    }

}
