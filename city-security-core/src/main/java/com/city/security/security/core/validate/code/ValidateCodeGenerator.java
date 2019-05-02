package com.city.security.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    public ValidateCode generate(ServletWebRequest request);
}
