package pe.com.project1.ms.application.gateway;

import pe.com.project1.ms.domain.customer.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> getCustomerById(String id);
}
