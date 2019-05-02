package com.city.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 页面管理
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/login")
    public String testPage(){
        return "login";
    }

    //成功页面
    @RequestMapping("/success")
    public String successPage(){
        return "success";
    }

    //失败页面
    @RequestMapping("/failure")
    public String failurePage(){
        return "failure";
    }


    //手机页面
    @RequestMapping("/mobilePage")
    public String smsPage(){
        return "smsPage";
    }

    //方法  和
    //注册页面
    @RequestMapping("/registerPage")
    public String registerPage(){
        return "register";
    }

    /**
     * 第三方账号的绑定页面
     * @return
     */
    @RequestMapping("/binding")
    public String bingdingPage(){
        return "binding";
    }

    /**
     * session失效页面
     */

    @RequestMapping("/invalidSession")
    @ResponseBody
    @ResponseStatus(code=HttpStatus.UNAUTHORIZED)
    public String invalidSession(){
        return "session失效了,请重新登录!";
    }
}
