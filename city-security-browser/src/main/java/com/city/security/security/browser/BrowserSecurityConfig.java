package com.city.security.security.browser;

import com.city.security.security.browser.session.CityExpiredSessionStrategy;
import com.city.security.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.city.security.security.core.properties.SecurityConstants;
import com.city.security.security.core.properties.SecurityProperties;
import com.city.security.security.core.validate.code.SmsCodeFilter;
import com.city.security.security.core.validate.code.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationSuccessHandler cityAuthenticationSuccessHandler;

    @Autowired
    @Qualifier("cityAuthenticationFailureHandler")
    private AuthenticationFailureHandler cityAuthenticationFailureHandler;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer citySocialSecurityConfig;



    //配置了这个类后会将所有的密码都进行加密比对
    @Bean
    public PasswordEncoder passwordEncoder(){
        //盐值加密
        return  new BCryptPasswordEncoder();
    }

    /**
     * 记住我的功能
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){

        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();

        tokenRepository.setDataSource(dataSource);
        //是否在启动的时候自动创建数据表 如果表已经存在就不能再次重新创建 注掉这行即可
//        tokenRepository.setCreateTableOnStartup(true);

        return tokenRepository;

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        applyPasswordAuthenticationConfig(http);


        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(cityAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        /**
         * 短信验证登录部分
         */
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setAuthenticationFailureHandler(cityAuthenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(securityProperties);
        smsCodeFilter.afterPropertiesSet();



//        http.httpBasic();//不使用表单登录 使用默认的登录方式
        /**
         * 在我们加入自定义页面的时候要进行如下的配置:
         * .antMatchers("/login.html").permitAll() //不需要身份认证
         * 不然会进入一直重定向
         */
        http
                .apply(smsAuthenticationSecurityConfig)
                .and()
                .apply(citySocialSecurityConfig)//社交登录
                .and()
//        .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()

                .loginPage("/page/login")//允许登录的界面
                .loginProcessingUrl("/authentication/form")//请求验证的接口
                .defaultSuccessUrl("/page/success")//成功的默认导向页
                .failureForwardUrl("/page/failure")
//       .successHandler(cityAuthenticationSuccessHandler) //请求成功的处理类
//        .failureHandler(cityAuthenticationFailureHandler)
                .and()
                .rememberMe()  //记住我的功能
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrower().getRememberMeSeconds())
                .userDetailsService(userDetailsService)
                .and()
                .sessionManagement()
                .invalidSessionUrl(securityProperties.getBrower().getSession().getSessionInvalidUrl())//session失效的地址
                .maximumSessions(securityProperties.getBrower().getSession().getMaximumSessions()) //设置session的最大数量 按用户名来判断的
                .maxSessionsPreventsLogin(true)//当达到session的最大数量时候阻止其他的登录,即踢下线
                .expiredSessionStrategy(new CityExpiredSessionStrategy())
                .and()
                .and()
                .authorizeRequests() //请求需要认证
                //"/static/**"表示所有用户均可访问的资源 必须加上静态访问的权限 不然页面会显示不全面
                .antMatchers(
                        "/static/**","/page/login","/page/failure","/page/mobilePage",
                        "/code/image","/code/sms","/authentication/mobile",securityProperties.getBrower().getSignUPUrl(),
                        "/user/register","/page/registerPage","/page/invalidSession"

                ).permitAll()
                //这里是硬编码权限 只限于简单的用户权限 这里的角色名称严格区分大小写
                //这里可以指定HttpMethod  如HttpMethod.GET,
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest()     //所有请求
                .authenticated() //都需身份认证
                .and()
                .csrf().disable() //跨站伪造请求禁用

        ;


    }

    private void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {

        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(cityAuthenticationSuccessHandler)
                .failureHandler(cityAuthenticationFailureHandler);
    }


}
