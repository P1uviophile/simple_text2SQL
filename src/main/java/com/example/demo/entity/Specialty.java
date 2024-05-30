package com.example.demo.entity;

import lombok.Data;

@Data
public class Specialty {
    private Integer specialty_id;
    private String specialty_name;
    private String specialty_link;
    private String province_area;
    private String province;
    private String city;
    private String county;
}
