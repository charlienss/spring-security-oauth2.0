package com.city.security.security.core.validate.code;

import com.city.security.security.core.properties.SecurityProperties;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 短信验证码生成器
 * create by charly 3/8
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {

        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());

        return new ValidateCode(code,
                securityProperties.getCode().getSms().getExpireIn()
        );
    }
}
