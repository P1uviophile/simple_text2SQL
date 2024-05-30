package com.example.demo.entity;

import lombok.Data;

@Data
public class Hotel {
    private Integer hotel_id;
    private String hotel_name;
    private String hotel_ads;
    private String score;
    private String province;
    private String city;
    private String county;
}
