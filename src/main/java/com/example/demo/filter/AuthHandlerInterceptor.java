package com.example.demo.filter;

import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    TokenUtil tokenUtil;
    @Value("${token.privateKey}")
    private String privateKey;
    @Value("${token.yangToken}")
    private Long yangToken;
    @Value("${token.oldToken}")
    private Long oldToken;
    /**
     * 权限认证的拦截操作.
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        log.info("=======进入拦截器========");
        log.info(httpServletRequest.getPathInfo());
        // 如果不是映射到方法直接通过,可以访问资源.
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        //为空就返回错误
        String token = httpServletRequest.getHeader("token");
        if (null == token || "".equals(token.trim())) {
            returnFalse(httpServletResponse);
            return false;
        }
        log.info("==============token:" + token);
        try {
            Map<String, String> map = tokenUtil.parseToken(token);
            String userId = map.get("userId");
            String userRole = map.get("userRole");
            long timeOfUse = System.currentTimeMillis() - Long.parseLong(map.get("timeStamp"));
            //1.判断 token 是否过期
            //年轻 token
            if (timeOfUse < yangToken) {
                log.info("年轻 token");
            }
            //老年 token 就刷新 token
            else if (timeOfUse >= yangToken && timeOfUse < oldToken) {
                httpServletResponse.setHeader("token",tokenUtil.getToken(userId,userRole));
            }
            //过期 token 就返回 token 无效.
            else {
                returnFalse(httpServletResponse);
                return false;
                //throw new TokenAuthExpiredException();
            }
            //2.角色匹配.
            if ("user".equals(userRole)) {
                log.info("========user账户============");
                return true;
            }
            if ("admin".equals(userRole)) {
                log.info("========admin账户============");
                return true;
            }
        }catch (Exception e){
            returnFalse(httpServletResponse);
            return false;
        }
        returnFalse(httpServletResponse);
        return false;
    }

    // token错误时返回数据 状态码-1
    private void returnFalse(HttpServletResponse response) throws IOException {
        Map<String, Object> res = new HashMap<>();
        res.put("code", "-1");
        res.put("msg", "token验证错误!");
        PrintWriter out = response.getWriter();
        out.append(res.toString());
    }
}

