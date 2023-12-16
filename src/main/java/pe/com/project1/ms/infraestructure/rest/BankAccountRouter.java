package pe.com.project1.ms.infraestructure.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BankAccountRouter {

    private static final String BANK_ACCOUNTS = "/bank-accounts";
    private static final String BANK_ACCOUNT_ID = BANK_ACCOUNTS + "/{id}";
    private static final String BAN_ACCOUNT_STATE = BANK_ACCOUNTS + "/{id}/state";

    @Bean
    public RouterFunction<ServerResponse> routes(BankAccountHandler bankAccountHandler) {
        return route(POST(BANK_ACCOUNTS).and(accept(APPLICATION_JSON)), bankAccountHandler::postBankAccount)
                .andRoute(GET(BANK_ACCOUNT_ID), bankAccountHandler::getBankAccountById)
                .andRoute(GET(BANK_ACCOUNTS), bankAccountHandler::getAllBankAccountsByAccountHolderId)
                .andRoute(PATCH(BAN_ACCOUNT_STATE).and(accept(APPLICATION_JSON)), bankAccountHandler::patchBankAccountState);
    }
}

