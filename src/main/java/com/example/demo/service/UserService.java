package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.stereotype.Service;
import com.example.demo.mapper.UserMapper;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public interface UserService {
    User queryByUserName(String user_name);
    int insert_user(String user_name, String password);

}
