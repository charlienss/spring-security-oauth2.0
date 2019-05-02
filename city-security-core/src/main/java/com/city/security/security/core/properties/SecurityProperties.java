package com.city.security.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "city.security")
public class SecurityProperties {

    private BroeserProperties brower = new BroeserProperties();

    private ValidateCodeproperties code= new ValidateCodeproperties();

    private SocialProperties social = new SocialProperties();

    public SocialProperties getSocial() {
        return social;
    }

    public void setSocial(SocialProperties social) {
        this.social = social;
    }

    public ValidateCodeproperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeproperties code) {
        this.code = code;
    }

    public BroeserProperties getBrower() {
        return brower;
    }

    public void setBrower(BroeserProperties brower) {
        this.brower = brower;
    }
}
