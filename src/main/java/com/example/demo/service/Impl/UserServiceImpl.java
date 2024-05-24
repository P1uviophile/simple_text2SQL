package com.example.demo.service.Impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public User queryByUserName(String user_name) {
        return userMapper.queryByUserName(user_name);
    }

    public int insert_user(String user_name, String password) {
        return  userMapper.insert_user(user_name, password);
    }
}
