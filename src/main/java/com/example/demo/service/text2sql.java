package com.example.demo.service;

import java.util.List;
import java.util.Map;

public interface text2sql {
    List<Map<String, Object>> text2dict(String input);
}
