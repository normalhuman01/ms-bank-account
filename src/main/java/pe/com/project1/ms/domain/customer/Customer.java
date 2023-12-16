package pe.com.project1.ms.domain.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String names;
    private String firstSurname;
    private String lastSurname;
    private String dni;
    private String email;
    private LocalDate birthdate;
    private CustomerType customerType;
}

