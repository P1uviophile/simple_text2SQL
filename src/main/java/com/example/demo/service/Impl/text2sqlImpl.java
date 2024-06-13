package com.example.demo.service.Impl;

import com.example.demo.DemoApplication;
import com.example.demo.service.text2sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import com.example.demo.DemoApplication.*;
import javax.sql.DataSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

@Component
public class text2sqlImpl implements text2sql {

    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        String sql = null;
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("POST");//默认get
            // 设置请求头，告诉服务器我们将发送JSON格式的数据
            conn.setRequestProperty("Content-Type", "application/json");
            // 设置允许输出
            conn.setDoOutput(true);
            // 构建JSON字符串
            JSONObject jsonInputString = new JSONObject();
            jsonInputString.put("question",param);
            try (OutputStream os = conn.getOutputStream()) {
                // 使用PrintWriter来写入JSON字符串
                try (PrintWriter writer = new PrintWriter(os, true)) {
                    writer.print(jsonInputString);
                }
            }
            // 连接，实际发送数据
            conn.connect();
            // 获取响应码
            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if(responseCode==200){
                // 定义BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null)
                    result.append(line);
                // 创建一个JSONObject对象
                JSONObject jsonObject = new JSONObject(result.toString());
                // 从JSONObject中获取sql字段的值
                sql = jsonObject.getString("sql");
                System.out.println(sql);
                return sql;
            }else{return null;}
        } catch (Exception e) {
            System.out.println("Post请求出现异常");
            e.printStackTrace();
        }//使用finally块来关闭输出流、输入流
        finally {try {if (in != null) in.close();} catch (IOException ex) {ex.printStackTrace();}}
        return sql;
    }

    @Override
    public List<Map<String, Object>> text2dict(String input,JdbcTemplate jdbcTemplate){
        String API = "https://u301332-87e4-379a512d.westb.seetacloud.com:8443/api/sql";
        String sql = sendPost(API,input);
        return jdbcTemplate.queryForList(sql);
        //return jdbcTemplate.queryForList("SELECT * FROM scenic_area");
    }

    public String textNot2Sql(String input){
        String API = "https://u301332-87e4-379a512d.westb.seetacloud.com:8443/api/text";
        return sendPost(API,input);
    }
}
