package org.objects;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String userpass;
    private String username;
    private String userId;

    public User(String userId, String username, String userpass, String email) {
        this.email = email;
        this.userpass = userpass;
        this.username = username;
        this.userId = userId;
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + userId +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + userpass + '\'' +
                '}';
    }
}
