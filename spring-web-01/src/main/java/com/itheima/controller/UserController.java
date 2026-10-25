package com.itheima.controller;

import cn.hutool.core.io.IoUtil;
import com.itheima.pojo.user;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {
//    @RequestMapping("/list")
//    public List<user> list(){
//        //加载并读取出user.list文件，获取用户数据
//        InputStream in  = this.getClass().getClassLoader().getResourceAsStream("user.txt");
//        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
//        //解析用户信息，封装为User对象->list集合
//        List<user> list = lines.stream().map(line -> {
//            String[] parts = line.split(",");
//            Integer id = Integer.parseInt(parts[0]);
//            String username = parts[1];
//            String password = parts[2];
//            String name = parts[3];
//            Integer age = Integer.parseInt(parts[4]);
//            LocalDateTime updateTime = LocalDateTime.parse(parts[5], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            return new user(id, username, password, name, age, updateTime);
//        }).toList();
//        //返回数据，json格式
//
//        return list;
//
//    }
    //方式一:属性注入
//    @Autowired
//    private UserService userService ;
    //方式二:构造器注入
//    private final UserService userService;
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
    //方式三:setter注入
//@Qualifier("userServiceImpl02")
//@Autowired
    @Resource(name = "userServiceImpl02")
    private UserService userService;
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping("/list")
    public List<user> list(){

        List<user> list = userService.list();
        //解析用户信息，封装为User对象->list集合

        //返回数据，json格式

        return list;

    }
}
