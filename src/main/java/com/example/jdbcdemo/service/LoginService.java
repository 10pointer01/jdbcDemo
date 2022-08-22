package com.example.jdbcdemo.service;

import com.example.jdbcdemo.pojo.Manager;

import java.util.List;

public interface LoginService {
    boolean login(Integer id,String password);

}
