package com.example.jwtdemo.model;

import com.example.jwtdemo.Entity.AuthenticationProvider;

public class CustomUserDto {

    private String name;
    private String username;
    private String password;
    private AuthenticationProvider authenticationprovider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationProvider getAuthenticationprovider() {
        return authenticationprovider;
    }

    public void setAuthenticationprovider(AuthenticationProvider authenticationprovider) {
        this.authenticationprovider = authenticationprovider;
    }
}
