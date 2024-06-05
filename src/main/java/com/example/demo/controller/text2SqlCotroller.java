package com.example.demo.controller;

import com.example.demo.commonResponse.ComResponse;
import com.example.demo.entity.Specialty;
import com.example.demo.service.Impl.text2sqlImpl;
import com.example.demo.service.text2sql;
import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class text2SqlCotroller {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private text2sql text2sql = new text2sqlImpl();

    @PostMapping("text2Sql")
    public ComResponse<List<Map<String, Object>>> text2Sql(@RequestParam("text") String text){
        ComResponse<List<Map<String, Object>>> objComResponse = new ComResponse<>();
        try{
            List<Map<String, Object>> list;
            list = text2sql.text2dict(text,jdbcTemplate);
            objComResponse.setData(list);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @PostMapping("textNot2Sql")
    public ComResponse<String> textNot2Sql(@RequestParam("text") String text){
        ComResponse<String> objComResponse = new ComResponse<>();
        try{
            String list;
            list = text2sql.textNot2Sql(text);
            objComResponse.setData(list);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }
}
