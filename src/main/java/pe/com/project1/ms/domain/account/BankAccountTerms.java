package pe.com.project1.ms.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountTerms {
    private double monthlyMaintenanceFee;
    private int maximumNumberOfTransactions;
}
