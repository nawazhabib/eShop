package com.habib.eshop.repository;

import com.habib.eshop.domain.User;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class UserRepositoryImpl implements UserRepository{
    private static final Set<User> USER_SET = new CopyOnWriteArraySet<>();

    @Override
    public void save(User user){
        USER_SET.add(user);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return USER_SET.stream().filter(user -> Objects.equals(user.getUsername(), username)).findFirst();
    }
}
