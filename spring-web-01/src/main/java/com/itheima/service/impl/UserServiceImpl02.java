package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.pojo.user;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
//@Primary
@Service
//@Component
public class UserServiceImpl02 implements UserService {
    @Autowired//程序运行时，会自动得
    private UserDao userDao;
    public List<user> list(){
        List<String> lines = userDao.list();
        List<user> list = lines.stream().map(line -> {
            String[] parts = line.split(",");
            Integer id = Integer.parseInt(parts[0]);
            String username = parts[1];
            String password = parts[2];
            String name = parts[3];
            Integer age = Integer.parseInt(parts[4]);
            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return new user(id+200, username, password, name, age, updateTime);
        }).toList();
        return list;
    }
}
