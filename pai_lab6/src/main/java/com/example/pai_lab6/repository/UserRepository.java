package com.example.pai_lab6.repository;

import com.example.pai_lab6.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByLogin(String login);
}
