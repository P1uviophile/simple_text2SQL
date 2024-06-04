package com.example.demo.controller;

import com.example.demo.service.Impl.text2sqlImpl;
import com.example.demo.service.text2sql;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TokenTestController {
    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * 使用 /test-token 测试 token，进过拦截器
     */
    @RequestMapping("/test-token")
    public Map testToken(HttpServletRequest request){
        String token = request.getHeader("token");
        return tokenUtil.parseToken(token);
    }

    @RequestMapping("/user/test")
    public void test(){
        text2sql text2sql = new text2sqlImpl();
        System.out.println("测试");
        List<Map<String, Object>> list = new ArrayList<>();
        list = text2sql.text2dict("上海黄浦区的景区有哪些?",jdbcTemplate);
        for (Map<String, Object> map : list) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
        }
    }
}
