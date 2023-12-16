package pe.com.project1.ms.infraestructure.repository.mongodb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.com.project1.ms.application.persistence.BankAccountRepository;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountType;
import pe.com.project1.ms.domain.exception.NotFoundException;
import pe.com.project1.ms.domain.product.ProductState;
import pe.com.project1.ms.infraestructure.model.dao.BankAccountDao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BankAccountReactiveMongoRepository implements BankAccountRepository {

    private final IBankAccountReactiveMongoRepository bankAccountReactiveMongoRepository;

    @Override
    public Mono<BankAccount> findByBankAccountNumber(String bankAccountNumber) {
        return bankAccountReactiveMongoRepository
                .findByBankAccountNumber(bankAccountNumber)
                .map(this::mapBankAccountDaoToBankAccount);
    }

    @Override
    public Mono<BankAccount> save(BankAccount bankAccount) {
        return bankAccountReactiveMongoRepository
                .save(new BankAccountDao(bankAccount))
                .map(this::mapBankAccountDaoToBankAccount);
    }

    @Override
    public Mono<BankAccount> updateBankAccountState(String bankAccountNumber, ProductState productState) {
        return bankAccountReactiveMongoRepository
                .findByBankAccountNumber(bankAccountNumber)
                .switchIfEmpty(Mono.error(new NotFoundException("No existe cuenta bancaria: " + bankAccountNumber)))
                .map(bankAccountDao -> this.updateState(bankAccountDao, productState))
                .flatMap(bankAccountDaoMono -> bankAccountDaoMono).map(this::mapBankAccountDaoToBankAccount);
    }

    @Override
    public Flux<BankAccount> findByAccountHolderId(String accountHolderId) {
        return bankAccountReactiveMongoRepository
                .findByCustomerId(accountHolderId)
                .doOnNext(account -> log.debug("Se encontro la siguiente cuenta: {}", account))
                .map(this::mapBankAccountDaoToBankAccount);
    }

    @Override
    public Flux<BankAccount> findAll() {
        return bankAccountReactiveMongoRepository.findAll().map(this::mapBankAccountDaoToBankAccount);
    }

    private Mono<BankAccountDao> updateState(BankAccountDao bankAccountDao, ProductState productState) {
        bankAccountDao.setProductState(productState);
        return bankAccountReactiveMongoRepository.save(bankAccountDao);
    }

    private BankAccount mapBankAccountDaoToBankAccount(BankAccountDao bankAccountDao) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(bankAccountDao.getId());
        bankAccount.setName(bankAccountDao.getName());
        bankAccount.setBankAccountNumber(bankAccountDao.getBankAccountNumber());
        bankAccount.setBalance(bankAccountDao.getBalance());
        bankAccount.setCustomerId(bankAccountDao.getCustomerId());
        bankAccount.setBankAccountType(bankAccountDao.getBankAccountType());
        bankAccount.setProductState(bankAccountDao.getProductState());
        bankAccount.setBankAccountTerms(bankAccountDao.getBankAccountTerms());
        bankAccount.setCreatedAt(bankAccountDao.getCreatedAt());
        return bankAccount;
    }

    @Override
    public Flux<BankAccount> findByBankAccountType(BankAccountType bankAccountType) {
        return bankAccountReactiveMongoRepository
                .findByBankAccountType(bankAccountType)
                .map(this::mapBankAccountDaoToBankAccount);
    }

    @Override
    public Mono<BankAccount> findById(String id) {
        return bankAccountReactiveMongoRepository
                .findById(id)
                .map(this::mapBankAccountDaoToBankAccount);
    }

    @Override
    public Mono<Boolean> existByAccountHolderIdAndAccountType(String customerId, BankAccountType bankAccounType) {
        return bankAccountReactiveMongoRepository.existsByCustomerIdAndBankAccountType(customerId, bankAccounType);
    }
}
