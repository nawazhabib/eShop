package com.habib.eshop.repository;

import com.habib.eshop.domain.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findByUsername(String username);
}
