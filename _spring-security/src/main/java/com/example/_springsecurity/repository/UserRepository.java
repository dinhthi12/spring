package com.example._springsecurity.repository;

import com.example._springsecurity.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccount_Username(String username);
}
