package com.space.datasource.controller;

import com.space.datasource.bean.User;
import com.space.datasource.mapper.test1.UserMapper1;
import com.space.datasource.mapper.test2.UserMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhuzhe
 * @date 2018/6/22 14:05
 * @email zhe.zhu1@outlook.com
 */
@RestController
public class UserController {

    @Autowired
    private UserMapper1 userMapper1;

    @Autowired
    private UserMapper2 userMapper2;

    @GetMapping("/getAll")
    public String getAll() {
        List<User> all1 = userMapper1.getAll();
        System.out.println(all1);

        List<User> all2 = userMapper2.getAll();
        System.out.println(all2);

        return "getAll";
    }
}
