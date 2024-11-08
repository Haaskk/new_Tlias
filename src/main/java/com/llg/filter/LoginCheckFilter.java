package com.llg.filter;

import com.alibaba.fastjson.JSONObject;
import com.llg.pojo.Result;
import com.llg.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 李龙
 * @version 1.0
 */
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //1.获取请求URL
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2.判断url是否包含login，如果包含，说明是在执行登陆操作，放行
        if (url.contains("login")) {
            log.info("登陆操作,放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3.获取请求头的令牌
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头的token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5.解析jwt令牌，如果解析失败，返回错误信息
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            log.info("解析失败，返回错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            e.getMessage();
            return;
        }

        //6.放行
        log.info("jwt令牌验证成功");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
