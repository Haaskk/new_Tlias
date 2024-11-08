package com.llg.intercepter;

import com.alibaba.fastjson.JSONObject;
import com.llg.pojo.Result;
import com.llg.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 李龙
 * @version 1.0
 */
@Component      //将工具或者配置类的类加入IOC容器
@Slf4j
public class LoginCheckIntercepter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {


        //1.获取请求URL
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2.判断url是否包含login，如果包含，说明是在执行登陆操作，放行
        if (url.contains("login")) {
            log.info("登陆操作,放行...");
            return true;
        }

        //3.获取请求头的令牌
        String jwt = req.getHeader("token");

        //4.判断令牌是否存在，如果不存在返回错误结果
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求头的token为空，返回未登录信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
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
            return false;
        }

        //6.放行
        log.info("jwt令牌验证成功");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
