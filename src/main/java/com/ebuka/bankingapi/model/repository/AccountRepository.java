package com.ebuka.bankingapi.model.repository;

import com.ebuka.bankingapi.model.entity.Account;
import com.ebuka.bankingapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUser(User user);
}
