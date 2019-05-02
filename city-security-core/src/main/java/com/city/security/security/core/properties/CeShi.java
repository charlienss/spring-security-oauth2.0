package com.city.security.security.core.properties;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CeShi {

    @Resource
    private SecurityProperties sec;

    @RequestMapping("/test")
    public  String testy(){
        return sec.getBrower().getName();
    }
}
