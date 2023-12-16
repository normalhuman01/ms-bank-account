package pe.com.project1.ms.infraestructure.repository.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import pe.com.project1.ms.domain.account.BankAccountType;
import pe.com.project1.ms.infraestructure.model.dao.BankAccountDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBankAccountReactiveMongoRepository extends ReactiveMongoRepository<BankAccountDao, String> {

    Mono<BankAccountDao> findByBankAccountNumber(String accountNumber);

    Flux<BankAccountDao> findByCustomerId(String accountHolderId);

    Flux<BankAccountDao> findByBankAccountType(BankAccountType bankAccountType);

    Mono<Boolean> existsByCustomerIdAndBankAccountType(String customerId, BankAccountType bankAccounType);
}
