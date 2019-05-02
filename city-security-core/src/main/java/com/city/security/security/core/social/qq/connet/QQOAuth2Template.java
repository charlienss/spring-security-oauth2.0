package com.city.security.security.core.social.qq.connet;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

public class QQOAuth2Template extends OAuth2Template {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        //这个配置后才有获取accessToken的响应
        setUseParametersForClientAuthentication(true);
    }


    @Override
    @SuppressWarnings("unchecked")
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        logger.info("accessTokenUrl:"+accessTokenUrl+"\n"+"parameters:"+parameters);

        /**
         * accessTokenUrl:https://graph.qq.com/oauth2.0/token
         *parameters:{
         * client_id=[101561211],
         * client_secret=[ad6f3b2fb59e7fc3f28b6389a259882f],
         *  code=[C27C9373F6A6C9129CEBBB3EE4258D99],
         *  redirect_uri=[http://www.pinzhi365.com/qqLogin/callback.do],
         *  grant_type=[authorization_code]
         *  }
         *
         *
         */
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

//        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);

        logger.info("获取accessToken的响应:"+responseStr);
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");

//        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr,"&");

        String accessToken = StringUtils.substringAfterLast(items[0],"=");
        Long expiresIn =new Long(StringUtils.substringAfterLast(items[1],"="));
        String refreshToken = StringUtils.substringAfterLast(items[2],"=");

        return new AccessGrant(
                accessToken,null,
                refreshToken,expiresIn
                );
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(
                new StringHttpMessageConverter(Charset.forName("UTF-8"))
        );
        return restTemplate;
    }


}

