package com.llg.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 李龙
 * @version 1.0
 */
//@WebFilter(urlPatterns = "/emps/*")
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init 初始化了");
    }

    @Override //进行拦截多次请求的方法
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("拦截了该次请求");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("拦截之后执行的操作");
    }

    @Override
    public void destroy() {
        System.out.println("destroy 销毁了");
    }
}
