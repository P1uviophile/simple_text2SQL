package com.example.demo.controller;

import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TokenTestController {
    @Autowired
    TokenUtil tokenUtil;
    /**
     * 使用 /test-token 测试 token，进过拦截器
     */
    @RequestMapping("/test-token")
    public Map testToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return tokenUtil.parseToken(token);
    }
}
