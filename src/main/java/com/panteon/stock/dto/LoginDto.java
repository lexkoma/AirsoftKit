package com.panteon.stock.dto;

public class LoginDto {
    private String login;
    private String password;

    public LoginDto(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // setters

    protected void setLogin(String login) {
        this.login = login;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    // getters

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
