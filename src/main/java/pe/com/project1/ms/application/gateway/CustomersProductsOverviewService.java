package pe.com.project1.ms.application.gateway;

import pe.com.project1.ms.domain.product.Product;
import reactor.core.publisher.Mono;

public interface CustomersProductsOverviewService {
    Mono<Product> postCustomersProductsOverview(Product product);
}
