package com.daily4work.daily22.dto.response;

public class AuthResponse {

    private String token;
    private String type = "Bearer";
    private Integer userId;
    private String email;
    private String name;
    private String surname;

    public AuthResponse() {
    }

    public AuthResponse(String token, Integer userId, String email, String name, String surname) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
