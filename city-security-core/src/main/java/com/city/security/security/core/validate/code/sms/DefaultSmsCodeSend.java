package com.city.security.security.core.validate.code.sms;

public class DefaultSmsCodeSend implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码:"+code);
    }
}
