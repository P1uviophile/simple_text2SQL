package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

@SpringBootApplication
//public class DemoApplication implements CommandLineRunner {
public class DemoApplication{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
/*
    @Override
    public void run(String... args) throws Exception {
        // 3、查询语句
        String selectUserData = "select * from hotel where hotel_ads LIKE '%威海%' ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(selectUserData);
        for (Map<String, Object> map : list) {
            System.out.println(map.entrySet());
/*
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
* /
        }
    }
*/
}
