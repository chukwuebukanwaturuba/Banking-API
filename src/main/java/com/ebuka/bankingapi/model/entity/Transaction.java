package com.ebuka.bankingapi.model.entity;

import com.ebuka.bankingapi.model.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions_tbl")
@Entity
public class Transaction extends BaseEntity {

    @Column(name = "trx_amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "narration")
    private String narration;

    @Column(name = "trx_type", nullable = false)
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "account_fk", nullable = false)
    private Account account;
}
