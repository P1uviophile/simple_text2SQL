package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("hotel")
    public ComResponse<List<Hotel>> getHotel(){
        ComResponse<List<Hotel>> objComResponse = new ComResponse<>();
        try{
            List<Hotel> hotels = hotelMapper.selectList(null);
            objComResponse.setData(hotels);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @GetMapping("specialty")
    public ComResponse<List<Specialty>> getSpecialty(){
        ComResponse<List<Specialty>> objComResponse = new ComResponse<>();
        try{
            List<Specialty> specialty = specialtyMapper.selectList(null);
            objComResponse.setData(specialty);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @GetMapping("scenic_area")
    public ComResponse<List<Scenic_area>> getScenic_area(){
        ComResponse<List<Scenic_area>> objComResponse = new ComResponse<>();
        try{
            List<Scenic_area> scenic_areas = scenic_area.selectList(null);
            objComResponse.setData(scenic_areas);
            return objComResponse;
        }catch (Exception e){
            objComResponse.setCode(500);
            objComResponse.setMsg("未知错误");
            return objComResponse;
        }
    }

    @GetMapping("province")
    public ComResponse<List<Province>> getProvince(){
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
