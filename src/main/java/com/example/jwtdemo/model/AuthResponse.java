package com.example.jwtdemo.model;

import java.io.Serializable;

public class AuthResponse implements Serializable {
    private static final long serialVersionUID = -8091879091924046844L;

    private final String jwttoken;

    private String userName;
    private String name;
    private String img;


    public AuthResponse(String jwttoken, String userName) {
        this.jwttoken = jwttoken;
        this.userName = userName;
    }

    public AuthResponse(String jwttoken, String userName, String name, String img) {
        this.jwttoken = jwttoken;
        this.userName = userName;
        this.name = name;
        this.img = img;
    }

    public String getJwttoken() {
        return this.jwttoken;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
