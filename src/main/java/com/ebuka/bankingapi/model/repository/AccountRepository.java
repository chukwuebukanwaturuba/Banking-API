package com.ebuka.bankingapi.model.repository;

import com.ebuka.bankingapi.model.entity.Account;
import com.ebuka.bankingapi.model.entity.User;
import com.ebuka.bankingapi.model.enums.AccountType;
import com.ebuka.bankingapi.model.enums.CurrencyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);

    Optional<Account> findAccountByAccountNumber(String accountNumber);

    Optional<Account> findAccountByAccountNumberAndCurrencyType(String accountNumber, CurrencyType currencyType);
}
