package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where user_name = #{user_name}")
    User queryByUserName(String user_name);

    @Insert("INSERT INTO user (user_name, password) VALUES (#{user_name}, #{password})")
    int insert_user(String user_name,String password);
}
