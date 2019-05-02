package com.city.security.controller;

import com.city.security.entity.User;
import com.city.security.serviece.UserService;
import com.sun.javafx.logging.PulseLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;

/**
 * 用户Controller
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ProviderSignInUtils providerSignInUtils;



    @RequestMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        User user = userService.findUserById(id);
        System.out.println("user==>"+user);
//        String name = securityProperties.getBrower().getName();
//        System.out.println("name==>"+name);
        return user;
    }

//通过上下文拿到用户的认证信息
//    @RequestMapping("/me")
//    public Object getCurrentUSer(){
//        return SecurityContextHolder.getContext().getAuthentication();
//    }
    @RequestMapping("/me")
    public Object getCurrentUSer(Authentication authentication){
        return authentication;
    }

    //注册新用户
    @PostMapping("/register")
    public String register(User user, HttpServletRequest request){
        System.out.println("来注册了=>"+user);
        userService.saveUser(user);
        //不管是绑定还是注册用用户,都会拿到一个用户的唯一标识
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(
                userId,
                new ServletWebRequest(request)
                );
        userService.saveUser(user);
        return "注册成功,下次可以直接QQ登录了";

//        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
//        String userId = user.getUsername();
//        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }


//    @PostMapping("/register")
//    public void regist(User user, HttpServletRequest request) {
//        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识。
//        String userId = user.getUsername();
//        System.out.println("userId=>"+userId);
//        //这里要把用户注册了才行
//        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
//    }



    //通过这个可以拿到用户的登录信息
    @RequestMapping("/userDetails")
    public Object getDetails(@AuthenticationPrincipal UserDetails user){
        return user;
    }






}
