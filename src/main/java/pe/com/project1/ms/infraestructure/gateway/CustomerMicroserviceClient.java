package pe.com.project1.ms.infraestructure.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.project1.ms.application.gateway.CustomerService;
import pe.com.project1.ms.domain.customer.Customer;
import pe.com.project1.ms.domain.exception.NotFoundException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerMicroserviceClient implements CustomerService {

    private final WebClient customerWebClient;

    @Override
    public Mono<Customer> getCustomerById(String id) {
        return customerWebClient.get()
                .uri(id)
                .retrieve()
                .bodyToMono(Customer.class)
                .switchIfEmpty(Mono.error(new NotFoundException("No se encontró un cliente con id: " + id)));
    }

}
