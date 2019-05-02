package com.city.security.security.core.social.qq.connet;

import com.city.security.security.core.social.qq.api.QQ;
import com.city.security.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth1.AbstractOAuth1ServiceProvider;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

//使用OAuth2Operations  OAuth2Template可返回实现

    /**
     * https://graph.qq.com/oauth2.0/show?
     * which=error&display=pc&error=100010&
     * which=Login&display=pc&client_id=1108307332&response_type=code&
     * redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fauth%2Fqq
     * &state=9de72832-4ac6-4415-9f4c-7dcc10238fe7
     *
     APP ID   1108307332

     APP KEY   9SGpkkypB7nBSNcg
     */
    public QQServiceProvider(String appId,String appSecret) {

        super(new QQOAuth2Template(
 appId,appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN
                ));
        this.appId=appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken,appId);
    }
}
