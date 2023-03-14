package com.example._springsecurity.service.Impl;

import com.example._springsecurity.models.User;
import com.example._springsecurity.repository.UserRepository;
import com.example._springsecurity.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
   @Autowired
   private UserRepository userRepository;

    @Override
    public User getByUsername(String username) {
        return this.userRepository.findByAccount_Username(username);
    }

    @Override
    public User save(User user) {
        return this.userRepository.save(user);
    }
}
