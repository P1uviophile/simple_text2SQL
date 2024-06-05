package com.example.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public interface text2sql {
    List<Map<String, Object>> text2dict(String input, JdbcTemplate jdbcTemplate);
    String textNot2Sql(String input);
}
