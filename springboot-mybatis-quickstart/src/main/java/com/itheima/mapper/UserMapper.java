package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper //应用程序在运行时，会自动为当前接口创建一个实现类对象（代理对象），并且会自动将该实现对象存入IOC容器 -bean
public interface UserMapper {

//    @Select("select * from users")
    public List<User> findAll();

    @Delete("delete from users where id = #{id}")
    public int deleteById(Integer id);
    @Insert("insert into users (username,password, name, age) values (#{username}, #{password}, #{name}, #{age})")
    public void insert(User user);
    @Update("update users set username = #{username},password = #{password},name = #{name},age = #{age} where id= #{id}")
    public void update (User user);
    @Select("select * from users where username = #{username} and password = #{password}")
    public User findByUserNamePassword(@Param("username") String username,@Param("password") String password);
}
