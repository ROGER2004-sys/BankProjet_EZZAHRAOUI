package org.sid.bank_account_service.entities;
import java.util.Date;

import jakarta.persistence.*;
import lombok.*;
import org.sid.bank_account_service.enums.AccountType;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccount {
    @Id
    private String id;
    private Date createdAt;
    private Double balance;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;
}
