package pe.com.project1.ms.infraestructure.model.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pe.com.project1.ms.domain.account.BankAccount;
import pe.com.project1.ms.domain.account.BankAccountTerms;
import pe.com.project1.ms.domain.account.BankAccountType;
import pe.com.project1.ms.domain.product.ProductState;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("bankAccounts")
public class BankAccountDao {
    @Id
    private String id;
    private String name;
    private String bankAccountNumber;
    private BigDecimal balance;
    private String customerId;
    private BankAccountType bankAccountType;
    private ProductState productState;
    private BankAccountTerms bankAccountTerms;
    private LocalDateTime createdAt;

    public BankAccountDao(BankAccount account) {
        this.id = account.getId();
        this.name = account.getBankAccountType() + " ACCOUNT";
        this.bankAccountNumber = account.getBankAccountNumber();
        this.balance = account.getBalance();
        this.customerId = account.getCustomerId();
        this.bankAccountType = account.getBankAccountType();
        this.productState = account.getProductState();
        this.bankAccountTerms = account.getBankAccountTerms();
        this.createdAt = account.getCreatedAt();
    }
}
