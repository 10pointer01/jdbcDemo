package com.example.jdbcdemo.service.Impl;

import com.example.jdbcdemo.mapper.UserMapper;
import com.example.jdbcdemo.pojo.User;
import com.example.jdbcdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public Integer delete(int id) {
        return userMapper.delete(id);
    }


    @Override
    public User get(int id) {
        return userMapper.get(id);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int check(int id,String password) {
        return userMapper.check(id,password);
    }
}

