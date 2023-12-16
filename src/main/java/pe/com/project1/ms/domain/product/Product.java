package pe.com.project1.ms.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Product {
    protected String id;
    protected String name;
    protected String customerId;
    protected LocalDateTime createdAt;
    protected ProductState productState;
}
