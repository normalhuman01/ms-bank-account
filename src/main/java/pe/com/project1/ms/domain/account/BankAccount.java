package pe.com.project1.ms.domain.account;

import lombok.*;
import pe.com.project1.ms.domain.product.Product;

import java.math.BigDecimal;

@ToString
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount extends Product {

    private String bankAccountNumber;
    private BigDecimal balance;
    private BankAccountType bankAccountType;
    private BankAccountTerms bankAccountTerms;

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

}
