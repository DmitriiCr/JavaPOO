package com.example.bank.repositories;

import com.example.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findBySortCodeAndAccountNumber(String sortCode, String accountNumber);
    Optional<Account> findByAccountNumber(String accountNumber);
}
