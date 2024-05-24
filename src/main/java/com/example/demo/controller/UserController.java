package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.utils.TokenUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping("login")
    public ResponseEntity<?> userLogin(@RequestParam("uname") String name, @RequestParam("password") String password) {
        // 检查用户名是否存在
        if (userService.queryByUserName(name) != null) {
            // 这里应该进一步验证密码，假设验证通过
            if (Objects.equals(userService.queryByUserName(name).getPassword(), password)) {
                // 登录成功，返回成功响应
                Map<String, Object> response = new HashMap<>();
                response.put("code", "0");
                response.put("msg", "登录成功");

                //登录成功后生成token并发送
                response.put("token", tokenUtil.getToken(name,"user"));
                // 可以添加用户信息或其他数据
                // response.put("data", someUserData);
                return ResponseEntity.ok(response);
            } else {
                // 密码错误，返回失败响应
                Map<String, Object> response = new HashMap<>();
                response.put("code", "1");
                response.put("msg", "密码错误");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            // 用户名不存在，返回失败响应
            Map<String, Object> response = new HashMap<>();
            response.put("code", "2");
            response.put("msg", "用户名不存在");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }

    @PostMapping("register")
    public ResponseEntity<?> userRegister(@RequestParam("uname") String name, @RequestParam("password") String password){
        // 检查用户名是否存在
        if (userService.queryByUserName(name) != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("code", "456");
            response.put("msg", "用户名已存在!");
            System.out.println(response.entrySet());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            try {
                userService.insert_user(name,password);
                // 用户名不存在，返回失败响应
                Map<String, Object> response = new HashMap<>();
                response.put("code", "0");
                response.put("msg", "注册成功!");
                System.out.println(response.entrySet());
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }catch (Exception e){
                System.out.println("用户注册发生未知错误");
                Map<String, Object> response = new HashMap<>();
                response.put("code", "456");
                response.put("msg", "用户注册发生未知错误");
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            }
        }
    }
}

