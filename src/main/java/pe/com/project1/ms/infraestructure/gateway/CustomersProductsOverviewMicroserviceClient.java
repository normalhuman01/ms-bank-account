package pe.com.project1.ms.infraestructure.gateway;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pe.com.project1.ms.application.gateway.CustomersProductsOverviewService;
import pe.com.project1.ms.domain.product.Product;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomersProductsOverviewMicroserviceClient implements CustomersProductsOverviewService {

    private final WebClient webClient;

    @Override
    public Mono<Product> postCustomersProductsOverview(Product product) {
        return webClient.post()
                .uri("/customers-products-overview")
                .body(Mono.just(product), Product.class)
                .retrieve()
                .bodyToMono(Product.class);
    }

}
