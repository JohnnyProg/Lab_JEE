package com.gryta.pai_security.dao;

import com.gryta.pai_security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
