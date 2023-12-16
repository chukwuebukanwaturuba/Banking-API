package com.ebuka.bankingapi.model.repository;

import com.ebuka.bankingapi.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByEmail(String email);
  boolean existsByEmail(String email);
}
