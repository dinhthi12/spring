package com.example._springsecurity.service;

import com.example._springsecurity.models.User;

public interface IUserService {
    User getByUsername(String username);
    User save (User user);
}
