package com.thc.pojo;

/**
 * @author : tanghaochen
 * create at:  2020-02-23  20:01
 * @program IPersistence_test
 * @description:
 */
public class User {

    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
