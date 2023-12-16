package pe.com.project1.ms.infraestructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.project1.ms.domain.product.ProductState;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStateAccountRequest {
    private String bankAccountNumber;
    private ProductState productState;
}
