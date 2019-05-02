//package com.city.web.configer;
//
//import com.city.web.TimeFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class WebConfig  extends WebMvcConfigurerAdapter {
//
//    @Autowired
//    private TimeInterecepter timeInterecepter;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(timeInterecepter);
////        super.addInterceptors(registry);
//    }
//
////    @Bean
////    public FilterRegistrationBean timeFilter2(){
////
////        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
////
////        TimeFilter timeFilter = new TimeFilter();
////
////        filterRegistrationBean.setFilter(timeFilter);
////
////        List<String> urls = new ArrayList<>();
////
////        urls.add("/*");
////
////        filterRegistrationBean.setUrlPatterns(urls);
////
////        return  filterRegistrationBean;
////    }
//}
