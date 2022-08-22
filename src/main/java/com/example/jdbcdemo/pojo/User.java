package com.example.jdbcdemo.pojo;

public class User {
    private  Integer id; //编号
    private  String username; //用户名
    private  String password; //密码
    private  Integer follower_id;

    public Integer getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(Integer follower_id) {
        this.follower_id = follower_id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(Integer id, String username, String password, Integer follower_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.follower_id = follower_id;
    }

    public User() {
    }
}

