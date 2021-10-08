package com.storemanager.model;

public class User {
    private String emailUser;
    private String passwordUser;

    public User(String email, String password) {
        this.emailUser = email;
        this.passwordUser = password;
    }

    public String getEmail() {
        return emailUser;
    }

    public void setEmail(String email) {
        this.emailUser = email;
    }

    public String getPassword() {
        return passwordUser;
    }

    public void setPassword(String password) {
        this.passwordUser = password;
    }
}
