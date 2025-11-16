package org.example.s4_jdbc.v2;

import java.util.Objects;

public class User {
    private final String userId;
    private final String passwrod;
    private final String name;
    private final String email;

    public User(String userId, String passwrod, String name, String email) {
        this.userId = userId;
        this.passwrod = passwrod;
        this.name = name;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(passwrod, user.passwrod) && Objects.equals(name, user.name) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, passwrod, name, email);
    }
}
