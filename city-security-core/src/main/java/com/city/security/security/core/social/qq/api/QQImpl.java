package com.city.security.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {

    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;

    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {


        //这里把accessToken作为请求的参数配置
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        System.out.println("QQImpl");
        //这里会将%s替换为accessToken
        String url = String.format(URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println("QQImpl==>"+result);

        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo gteUserInfo() {

        String url = String.format(URL_GET_USERINFO,appId,openId);

        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println("gteUserInfo==>"+result);

        QQUserInfo userInfo = null;

        try {
            System.out.println("进来了-----");
            userInfo = objectMapper.readValue(result, QQUserInfo.class);

            userInfo.setOpenId(openId);

            System.out.println("userInfo=>"+userInfo);

            return userInfo;

        } catch (Exception e) {
            throw  new RuntimeException("获取用户信息失败");
        }
//        return null;
    }

//    public String getAppId() {
//        return appId;
//    }
//
//    public void setAppId(String appId) {
//        this.appId = appId;
//    }
//
//    public String getOpenId() {
//        return openId;
//    }
//
//    public void setOpenId(String openId) {
//        this.openId = openId;
//    }
}
