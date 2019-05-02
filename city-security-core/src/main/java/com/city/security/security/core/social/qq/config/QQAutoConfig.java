package com.city.security.security.core.social.qq.config;

import com.city.security.security.core.properties.QQProperties;
import com.city.security.security.core.properties.SecurityProperties;
import com.city.security.security.core.social.CityConnectView;
import com.city.security.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
@ConditionalOnProperty(prefix = "city.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;


    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        QQProperties qqConfig = securityProperties.getSocial().getQq();

        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());

    }

    @Bean({"connect/qqConnect", "connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View weixinConnectedView() {
        return new CityConnectView();
    }




}
