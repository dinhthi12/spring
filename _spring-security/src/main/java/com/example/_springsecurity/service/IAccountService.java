package com.example._springsecurity.service;

import com.example._springsecurity.models.Account;

import java.util.Optional;

public interface IAccountService {
    Optional<Account> getById(String username);
    Boolean isUsernameExists(String username);

    Account save(Account account);
}
