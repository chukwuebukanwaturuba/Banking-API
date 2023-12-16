package com.ebuka.bankingapi.model.entity;

import com.ebuka.bankingapi.model.enums.AccountType;
import com.ebuka.bankingapi.model.enums.CurrencyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "account_tbl")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "acc_number", nullable = false)
    private String accountNumber;

    @Column(name = "acc_balance")
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private Set<Transaction> transactions = new HashSet<>();

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;

    @Column(name = "acc_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Enumerated(EnumType.STRING)
    @Column(name = "acc_ccy_type", nullable = false)
    private CurrencyType currencyType;
}
