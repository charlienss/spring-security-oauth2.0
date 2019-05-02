package com.city.security.security.browser.authentication;

import com.city.security.security.core.properties.SecurityProperties;
import com.city.security.security.core.properties.LoginType;
import com.city.security.security.core.validate.code.ValidateCodeController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败后的处理类
 */
@Component("cityAuthenticationFailureHandler")
public class CityAuthenticationFailureHandler  extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();



    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        logger.info("登录失败了!");

//        response.sendRedirect("failure.html");

        logger.info("LoginType.JSON=>"+LoginType.JSON);
        logger.info("securityProperties.getBrower().getLoginType()=>"+
                securityProperties.getBrower().getLoginType());

//        Object attribute = sessionStrategy.getAttribute(new ServletWebRequest(request),"SESSION_KEY_IMAGE_CODE");
//        System.out.println("attribute=>"+attribute);


        if (LoginType.JSON.equals(securityProperties.getBrower().getLoginType())){
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception));
        }else {
            super.onAuthenticationFailure(request,response,exception);
        }


    }
}
