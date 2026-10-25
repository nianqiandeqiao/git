package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class user {
    private Integer id;
    private String name;
    private String password;
    private String username;
    private Integer age;
    private LocalDateTime updateTime;
}
