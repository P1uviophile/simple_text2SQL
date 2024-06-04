package com.example.demo;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        for (Map<String, Object> map : jdbcTemplate.queryForList("SELECT view_name FROM scenic_area WHERE province = '河北省'")) {
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
