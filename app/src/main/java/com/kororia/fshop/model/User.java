package com.kororia.fshop.model;

/**
 * Created by Asahi on 7/6/2017.
 */


public class User {

    private AccountOption loginOption;
    private String userName;
    private String password;
    private String confirmPassword;
    private String name;
    private String email;
    private String phone;

    public User(String phone) {
        new User(null, null, null, null, phone, AccountOption.Phone);
    }

    public User(String email, String password) {
        new User(email, null, password, null, null, AccountOption.EmailAndPassword);
    }

    public User(String email, String userName, String password, String confirmPassword, String phone, AccountOption loginOption) {
        this.email = email;

        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = userName;
        this.loginOption = loginOption;
    }

    public AccountOption getLoginOption() {
        return loginOption;
    }

    public void setLoginOption(AccountOption loginOption) {
        this.loginOption = loginOption;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
