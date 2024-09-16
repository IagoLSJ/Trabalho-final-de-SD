package org.entities;

public class Password {
    private String title;
    private String password;
    private String userId;

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Password{" +
                "title=" + title +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
