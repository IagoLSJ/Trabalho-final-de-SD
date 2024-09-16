package org.entities;

import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String email;
    private String username;
    private String userpass;

    public User(String username, String userpass, String email) {
        this.email = email;
        this.userpass = userpass;
        this.username = username;
        this.id = generateUserId();
    }

    public User() {
        this.id = generateUserId();
    }

    // Gera um UUID aleatório para userId
    private String generateUserId() {
        return UUID.randomUUID().toString();
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

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + userpass + '\'' +
                '}';
    }
}
