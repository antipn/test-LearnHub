package com.test.antipn.dto;

public class UserDto {
    private String login;
    private String email;
    private String phone;
    private String password;

    public UserDto() {
    }

    public UserDto(String login, String email, String phone, String password) {
        this.login = login;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
