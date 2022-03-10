package com.habib.eshop.repository;

import domain.User;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class UserRepositoryImpl implements UserRepository{
    private static final Set<User> USER_SET = new CopyOnWriteArraySet<>();

    @Override
    public void save(User user){
        USER_SET.add(user);
    }
}
