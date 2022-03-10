package com.habib.eshop.service;

import com.habib.eshop.dto.UserDTO;
import com.habib.eshop.repository.UserRepository;
import domain.User;

public class UserServiceImpl implements UserService{
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(UserDTO userDTO){
        String encrypted = encryptPassword(userDTO.getPassword());
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());

        userRepository.save(user);
    }
    private String encryptPassword(String password){
        //we will implement it later
        return password;
    }
}
