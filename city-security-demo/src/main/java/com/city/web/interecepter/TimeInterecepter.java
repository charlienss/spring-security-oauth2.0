//package com.city.web.interecepter;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Date;
//
//@Component
//public class TimeInterecepter implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("preHandle");
//        request.setAttribute("startTime", new Date().getTime());
//        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
//        System.out.println(((HandlerMethod) handler).getMethod().getName());
//        return true; //这里控制是否调用后面的方法 建议返回true
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("preHandle");
//        Long start = (Long) request.getAttribute("startTime");
//        System.out.println("time intercepter 耗时:" + (new Date().getTime() - start));
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("afterCompletion");
//        Long start = (Long) request.getAttribute("startTime");
//        System.out.println("time intercepter 耗时:" + (new Date().getTime() - start));
//        System.out.println("ex is "+ex);
//
//    }
//
//}
