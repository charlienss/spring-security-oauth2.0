package com.city.security.security.core.authorize;

import com.city.security.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

//配置permitAll的路径
@Component
public class CityAuthorizeConfigProvider implements AuthorizeConfigProvider {
    @Autowired
    private SecurityProperties securityProperties;
    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                "/static/**","/page/login","/page/failure","/page/mobilePage",
                "/code/image","/code/sms","/authentication/mobile",securityProperties.getBrower().getSignUPUrl(),
                "/user/register","/page/registerPage","/page/invalidSession","/page/logoutSuccess",securityProperties.getBrower().getSignOutUrl()


        )
                .permitAll();
    }
}
