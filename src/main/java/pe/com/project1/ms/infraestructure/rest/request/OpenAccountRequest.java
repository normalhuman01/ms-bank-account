package pe.com.project1.ms.infraestructure.rest.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pe.com.project1.ms.domain.account.BankAccountType;
import pe.com.project1.ms.domain.customer.CustomerType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpenAccountRequest {
    private String accountHolderId;
    private BankAccountType bankAccountType;
    private CustomerType customerType;
    private List<String> accountHoldersId;
    private List<String> authorizedSignersId;
}
