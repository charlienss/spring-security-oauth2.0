package com.city.security.security.browser.authentication;

import com.city.security.security.core.properties.SecurityProperties;
import com.city.security.security.core.properties.LoginType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功后的处理类
 */
@Component("cityAuthenticationSuccessHandler")
public class CityAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        logger.info("登录成功了!");

        if (LoginType.JSON.equals(securityProperties.getBrower().getLoginType())){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
//            response.sendRedirect();
//            request.getRequestDispatcher("http://localhost:8080/page/success");
        }else {
            super.onAuthenticationSuccess(request,response,authentication);
        }



    }
}
