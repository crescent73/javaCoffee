package com.coffee.po;

import java.util.Objects;

public class User {
    String name;
    String password;
    String userType;
    Long id;

    public User() {
    }

    public User(String name, String password, String userType, Long id) {
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name)  &&
                userType.equals(user.userType) &&
                id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, userType, id);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", id=" + id +
                '}';
    }


}
