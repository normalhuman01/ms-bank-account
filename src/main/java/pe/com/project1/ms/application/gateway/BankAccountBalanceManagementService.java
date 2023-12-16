package pe.com.project1.ms.application.gateway;

import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountBalance;
import reactor.core.publisher.Mono;

public interface BankAccountBalanceManagementService {

	Mono<BankAccountBalance> postBankAccountBalanceManagement(BankAccount bankAccount);

}
