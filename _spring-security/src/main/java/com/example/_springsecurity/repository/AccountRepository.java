package com.example._springsecurity.repository;

import com.example._springsecurity.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Boolean existsByUsername(String username);
}
