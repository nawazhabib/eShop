package com.habib.eshop.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserDTO {
    @NotEmpty
    @Size(min = 4, max = 32)
    private String username;

    @NotEmpty
    @Size(min = 6, max = 16)
    private String password;

    @NotEmpty
    @Size(min = 4, max = 16)
    private String passwordConfirmed;

    @NotEmpty
    @Size(min = 4, max = 64)
    @Email
    private String email;

    @NotEmpty
    @Size(min = 1, max = 31)
    private String firstName;

    @NotEmpty
    @Size(min = 1, max = 31)
    private String lastName;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirmed(String passwordConfirmed) {
        this.passwordConfirmed = passwordConfirmed;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmed() {
        return passwordConfirmed;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
