package com.example.jdbcdemo.mapper;


import com.example.jdbcdemo.pojo.Manager;
import com.example.jdbcdemo.pojo.User;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Map;

/**
 * 数据访问层
 */
@Mapper
public interface ManagerMapper {
    //查询全部
    @Select("select * from manager")
    List<Manager> findAll();

    //新增数据
    @Insert(" insert into  manager( managername,password) values (#{managername},#{password}) ")
    public int save(Manager manager);

    //删除数据
    @Delete(" delete from manager where id= #{id} ")
    public int delete(int id);

    //根据id查找
    @Select("select * from manager where id= #{id} ")
    public Manager get(int id);

    //更新数据
    @Update("update manager set managername=#{managername},password=#{password} where id=#{id} ")
    public int update(Manager manager);

    @Select("select count(*) from manager where id= #{id} and password= #{password} ")
    public int check(int id,String password);

    @Select("select * from user where follower_id= #{id} ")
    public List<User> getList(int id);

    @Select(" select username,managername,vistername from (user left join manager on user.id=manager.id) left join vister on user.id=vister.id")
    public List<Map<String,String>> test();
}