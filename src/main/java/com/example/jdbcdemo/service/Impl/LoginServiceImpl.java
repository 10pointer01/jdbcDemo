package com.example.jdbcdemo.service.Impl;

import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.service.LoginService;
import com.example.jdbcdemo.service.ManagerService;

import javax.annotation.Resource;
import java.util.List;

public class LoginServiceImpl implements LoginService {
    @Resource
    private ManagerService managerService;

    @Override
    public boolean login(Integer id,String password) {

        return managerService.check(id, password) > 0;
    }


}
