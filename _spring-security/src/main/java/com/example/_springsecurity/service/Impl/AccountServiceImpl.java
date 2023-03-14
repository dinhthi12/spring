package com.example._springsecurity.service.Impl;

import com.example._springsecurity.models.Account;
import com.example._springsecurity.repository.AccountRepository;
import com.example._springsecurity.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Optional<Account> getById(String username) {
        return this.accountRepository.findById(username);
    }

    @Override
    public Boolean isUsernameExists(String username) {
        return this.accountRepository.existsByUsername(username);
    }

    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }


}
