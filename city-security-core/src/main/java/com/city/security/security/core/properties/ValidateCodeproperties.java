package com.city.security.security.core.properties;

/**
 * 验证码配置
 *
 */
public class ValidateCodeproperties {

    private  ImageCodeProperties image =  new ImageCodeProperties();
    private  SmsCodeProperties sms =  new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }
}
