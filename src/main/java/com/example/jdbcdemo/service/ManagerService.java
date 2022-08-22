package com.example.jdbcdemo.service;
import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.pojo.User;

import java.util.List;
import java.util.Map;

public interface ManagerService {
    //查询全部
    List<Manager> findAll();
    //新增数据
    int save(Manager manager);
    //删除数据
    Integer delete(int id);
    //根据id查找
    Manager get(int id);
    //更新数据
    int update(Manager manager);
    //用户校验
    int check(int id,String password);
    //查询所有下属
    List<User> getList(int id);

    List<Map<String,String>> test();

}
