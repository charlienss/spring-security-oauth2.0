package com.city.security.security.core.properties;

import com.city.security.security.core.properties.LoginType;

public class BroeserProperties {

    private SessionProperties session = new SessionProperties();

    private String signUPUrl="/register.html";
    //    private String signUPUrl="/page/registerPage";
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private  String name;

    private LoginType loginType = LoginType.JSON;

    private String signOutUrl ="/logoutSuccess.html" ;

    //配置记住我的时常秒数(通常建议定一周左右)
    private int rememberMeSeconds = 3600;

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUPUrl() {
        return signUPUrl;
    }

    public void setSignUPUrl(String signUPUrl) {
        this.signUPUrl = signUPUrl;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
