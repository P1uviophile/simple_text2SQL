package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.commonResponse.ComResponse;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Province;
import com.example.demo.entity.Scenic_area;
import com.example.demo.entity.Specialty;
import com.example.demo.mapper.HotelMapper;
import com.example.demo.mapper.ProvinceMapper;
import com.example.demo.mapper.Scenic_areaMapper;
import com.example.demo.mapper.SpecialtyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("search")
public class MapController {

    @Autowired
    HotelMapper hotelMapper;

    @Autowired
    SpecialtyMapper specialtyMapper;

    @Autowired
    Scenic_areaMapper scenic_area;

    @Autowired
    ProvinceMapper provinceMapper;

    @PostMapping("hotel")
    public ComResponse<List<Hotel>> getHotel(@RequestParam("searchQuery") String searchQuery){
        ComResponse<List<Hotel>> objComResponse = new ComResponse<>();
        try{
            QueryWrapper<Hotel> wrapper = new QueryWrapper<>();
            wrapper.like("hotel_name", searchQuery).or().like("hotel_ads", searchQuery).or().like("province", searchQuery).or().like("city", searchQuery).or().like("county", searchQuery);
            List<Hotel> hotels = hotelMapper.selectList(wrapper);
            objComResponse.setData(hotels);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @PostMapping("specialty")
    public ComResponse<List<Specialty>> getSpecialty(@RequestParam("searchQuery") String searchQuery){
        ComResponse<List<Specialty>> objComResponse = new ComResponse<>();
        try{
            QueryWrapper<Specialty> wrapper = new QueryWrapper<>();
            wrapper.like("specialty_name", searchQuery).or().like("province_area", searchQuery).or().like("province", searchQuery).or().like("city", searchQuery).or().like("county", searchQuery);
            List<Specialty> specialty = specialtyMapper.selectList(wrapper);
            objComResponse.setData(specialty);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @PostMapping("scenic_area")
    public ComResponse<List<Scenic_area>> getScenic_area(@RequestParam("searchQuery") String searchQuery){
        ComResponse<List<Scenic_area>> objComResponse = new ComResponse<>();
        try{
            QueryWrapper<Scenic_area> wrapper = new QueryWrapper<>();
            wrapper.like("view_name", searchQuery).or().like("level", searchQuery).or().like("province", searchQuery).or().like("city", searchQuery).or().like("county", searchQuery);
            List<Scenic_area> scenic_areas = scenic_area.selectList(wrapper);
            objComResponse.setData(scenic_areas);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @PostMapping("province")
    public ComResponse<List<Province>> getProvince(@RequestParam("searchQuery") String searchQuery){
        ComResponse<List<Province>> objComResponse = new ComResponse<>();
        try{
            List<Province> provinces = provinceMapper.selectList(null);
            objComResponse.setData(provinces);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }
}
