package com.example.jdbcdemo.mapper;


import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.pojo.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 数据访问层
 */
@Mapper
public interface UserMapper {
    //查询全部
    @Select("select * from user")
    List<User> findAll();

    //新增数据
    @Insert(" insert into  user( username,password,follower_id ) values (#{username},#{password},#{follower_id}) ")
    public int save(User user);

    //删除数据
    @Delete(" delete from user where id= #{id} ")
    public int delete(int id);

    //根据id查找
    @Select("select * from user where id= #{id} ")
    public User get(int id);

    //更新数据
    @Update("update user set username=#{username},password=#{password},follower_id=#{follower_id} where id=#{id} ")
    public int update(User user);

    @Select("select count(*) from user where id= #{id} and password= #{password} ")
    public int check(int id,String password);

}

