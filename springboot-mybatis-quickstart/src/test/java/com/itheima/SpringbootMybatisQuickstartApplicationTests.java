package com.itheima;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootMybatisQuickstartApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public  void testDeleteById(){
        int i =userMapper.deleteById(4);
        System.out.println("执行完毕，影响的记录数 "+i);
    }
    @Test
    public void testFindAll(){
        List<User> userList = userMapper.findAll();
        userList.forEach(System.out::println);
    }
    @Test
    public void testInsert(){
        User user = new User(null,"qiaoshao","123456","qiaoshao",18);
        userMapper.insert(user);
    }
    @Test
    public void testUpdate(){
        User user = new User(6,"qiaojiangjun","2q342134","qiaojaingjun",18);
        userMapper.update(user);
    }

    @Test
    public void testFindByUserNamePassword(){
        User user = userMapper.findByUserNamePassword("daqiao","123456");
        System.out.println(user);

    }
}
