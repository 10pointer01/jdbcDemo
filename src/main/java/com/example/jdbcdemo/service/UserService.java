package com.example.jdbcdemo.service;

import com.example.jdbcdemo.pojo.User;
import java.util.List;

public interface UserService {
    //查询全部
    List<User> findAll();
    //新增数据
    int save(User user);
    //删除数据
    Integer delete(int id);
    //根据id查找
    User get(int id);
    //更新数据
    int update(User user);
    //用户校验
    int check(int id,String password);
}

