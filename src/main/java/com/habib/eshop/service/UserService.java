package com.habib.eshop.service;

import com.habib.eshop.domain.User;
import com.habib.eshop.dto.LoginDTO;
import com.habib.eshop.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);

    boolean isNotUniqueUsername(UserDTO user);

    User verifyUser(LoginDTO loginDTO);

}
