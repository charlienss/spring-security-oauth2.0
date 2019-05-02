package com.city.security.security.core.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 这个是自动注册的必要类
 * 只有这个才能自动注册不走注册页面
 */
@Component("myConnectionSignUp")
public class MyConnectionSignUp implements ConnectionSignUp {

    public String execute(Connection<?> connection) {
        //根据社交用户信息，默认创建用户并返回用户唯一标识
        return connection.getDisplayName();

    }
}
