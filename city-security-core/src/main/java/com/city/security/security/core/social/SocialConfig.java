
package com.city.security.security.core.social;

import com.city.security.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@Order(1)
public class SocialConfig extends SocialConfigurerAdapter {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

//    @Autowired
//    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired(required = false)
    private ConnectionSignUp myConnectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(
                dataSource,
                connectionFactoryLocator, Encryptors.noOpText());

        repository.setTablePrefix("city_");

        if(myConnectionSignUp != null){
            repository.setConnectionSignUp(myConnectionSignUp);
        }

        return repository;

//        return super.getUsersConnectionRepository(connectionFactoryLocator);
    }

    @Bean
    public SpringSocialConfigurer citySocialSecurityConfig() {

        String filterProcessesUrl = securityProperties.getSocial().getFilterProcessesUrl();

        CitySpringSocialConfigurer configurer = new  CitySpringSocialConfigurer(filterProcessesUrl);

        //如果找不到用户就跳转到这个页面
        configurer.signupUrl(
                securityProperties.getBrower().getSignUPUrl()
        );

        return configurer;
    }


    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator){

        return new ProviderSignInUtils(factoryLocator,
                getUsersConnectionRepository(factoryLocator));

    }
}
