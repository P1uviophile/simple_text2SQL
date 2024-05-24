package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("hello")
    public Map<String, Object> hello() {
        // 创建一个用于存储返回结果的Map对象
        Map<String, Object> map = new HashMap<>();
        // 向Map中添加名为"name"的键值对，值为"张三"
        map.put("name", "张三");
        // 向Map中添加名为"age"的键值对，值为25
        map.put("age", 25);
        // 返回存储结果的Map对象
        return map;
    }
}