package com.example.jdbcdemo.pojo;

public class Manager {

    private  Integer id;
    private String managername;
    private String password;
    private Integer master_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaster_id() {
        return master_id;
    }

    public void setMaster_id(Integer master_id) {
        this.master_id = master_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }


    public Manager(){
    }

    public Manager(Integer id, String managername, String password, Integer master_id) {
        this.id=id;
        this.managername=managername;
        this.password = password;
        this.master_id = master_id;
    }
}
