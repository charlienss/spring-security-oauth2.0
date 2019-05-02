package com.city.web;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

//@Component
public class TimeFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("时间过滤器初始化!");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("time filter start");
        long start = new Date().getTime();

        chain.doFilter(request,response);
        System.out.println("time filter:"+(new Date().getTime()-start));

        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("时间过滤器销毁!");
    }
}
