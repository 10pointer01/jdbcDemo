package com.example.jdbcdemo.service.Impl;

import com.example.jdbcdemo.mapper.ManagerMapper;
import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.pojo.User;
import com.example.jdbcdemo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ManagerServiceImpl implements ManagerService{

    @Autowired
    @Resource
    private ManagerMapper managerMapper;

    @Override
    public List<Manager> findAll() {
        return managerMapper.findAll();
    }

    @Override
    public int save(Manager manager) {
        return managerMapper.save(manager);
    }

    @Override
    public Integer delete(int id) {
        return managerMapper.delete(id);
    }

    @Override
    public Manager get(int id) {
        return managerMapper.get(id);
    }

    @Override
    public int update(Manager manager) {
        return managerMapper.update(manager);
    }

    @Override
    public int check(int id,String password) {
        return managerMapper.check(id,password);
    }

    @Override
    public List<User> getList(int id) {
        return managerMapper.getList(id);
    }

    @Override
    public List<Map<String,String>> test() {
        return managerMapper.test();
    }
}
