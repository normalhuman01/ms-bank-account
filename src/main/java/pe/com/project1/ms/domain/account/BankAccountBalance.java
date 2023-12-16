package pe.com.project1.ms.domain.account;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountBalance {
	private String id;
	private String bankAccountId;
	private BigDecimal balance;
	private Integer transactionCountLimit;
}
