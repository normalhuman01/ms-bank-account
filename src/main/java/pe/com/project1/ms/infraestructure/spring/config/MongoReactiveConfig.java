package pe.com.project1.ms.infraestructure.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.ReactiveMongoTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.reactive.TransactionalOperator;

@Configuration
public class MongoReactiveConfig {

    @Bean
    public TransactionalOperator transactionalOperator(ReactiveTransactionManager txm) {
        return TransactionalOperator.create(txm);
    }

    @Bean
    public ReactiveTransactionManager transactionManager(ReactiveMongoDatabaseFactory dbf) {
        return new ReactiveMongoTransactionManager(dbf);
    }

}
