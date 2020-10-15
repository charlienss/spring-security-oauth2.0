# Spring-Security-Oauth2.0浏览器端的登录项目分享

![](https://img.shields.io/badge/spring--social--security-1.1.6.RELEASE-brightgreen.svg) ![](https://img.shields.io/github/forks/fengcharly/spring-security-oauth2.0.svg) ![](https://img.shields.io/github/stars/fengcharly/spring-security-oauth2.0.svg) ![![](https://img.shields.io/badge/thymeleaf-3.0.2.RELEASE-brightgreen.svg)](https://img.shields.io/badge/springboot-1.0.0--SNAPSHOT-blue.svg) ![](https://img.shields.io/badge/thymeleaf-3.0.2.RELEASE-brightgreen.svg) ![](https://img.shields.io/twitter/url/https/github.com%2Ffengcharly%2Fspring-security-oauth2.0.svg)

### 1.简介

​	CitySecurity项目是本人为登录鉴权的处理制作的一个Demo,这里主要介绍浏览器端的登录.本项目使用了SpringSecurity实现表单安全登录、图形验证的校验、记住我时长控制机制、第三方登录.比较独特的一点是集合SpringSocial做第三方登录的支持(此处本人测试自用app-id和app-secret,如果需要测试可以帮忙点下星发邮件给我,会尽快给与回复,谢谢支持!).该方案是目前本人觉得比较完善的一套安全登录的方式,前端页面设计也是本人制作,比较简洁,当然你也可以用自己的一套比较漂亮的UI,此处仅供参考,有什么好的建议都可以给予反馈.该框架在[Spring官方网站](https://spring.io/projects)上也有相关的文档介绍,喜欢本项目的伙伴可以给我点下星,支持一下,当然可以与我交流,共同学习,共同进步!

![](https://img2018.cnblogs.com/blog/1373932/201905/1373932-20190502212242634-705247316.png)

### 2.接口说明

​	接口方面均为本地测试,本项目附带了本人oauth.sql的建表文件,导入即可.关于第三方登录测试说明:此处本人测试自用app-id和app-secret,如果需要测试可以帮忙点下星★,然后[发邮件给我](mailto:fengbaichaoZK@163.com),会尽快给与回复,谢谢支持!

```java
1.登录测试:
	访问URL: http://localhost:8060/page/success
	如果未登录会直接跳到登录页: http://localhost:8060/page/login
2.登录账号说明:
     此处未读取数据库数据,主要是方便测试,相关的用户信息写入的地方我在代码中注释很清楚,大家可以查看,使用JPA或者其他方式读取数据库数据来进行比对.
     账户名(任意填写)
     密码(123456)
     验证码(未做点击刷新的动作,需要刷新页面)
     记住我(默认是1小时,相关地方可以查看代码注解)
     第三方登录
3.登录成功后会跳转到相关的成功页面
4.用户信息显示(登陆后)
	URL: http://localhost:8060/user/me
```

### 3.代码简介

​	此项目本人构建的是Maven多模块工程,主要依赖父工程city-security,子工程有city-security-core、city-security-browser、city-security-app、city-security-demo；几个比较重要的模块代码如下：

##### Maven：

```xml
 <dependency>
   <groupId>org.springframework.social</groupId>
   <artifactId>spring-social-security</artifactId>
   <version>1.1.6.RELEASE</version>
 </dependency>

  <dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-oauth2</artifactId>
  </dependency>

  <dependency>
      <groupId>org.springframework.social</groupId>
      <artifactId>spring-social-core</artifactId>
  </dependency>
```

##### Browser核心配置:

```java
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
//      .addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
//      .addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
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
        .anyRequest()     //所有请求
        .authenticated() //都需身份认证
        .and()
        .csrf().disable() //跨站伪造请求禁用
        ;
```

##### 用户账户配置:

```java
private SocialUserDetails buildUser(String userId) {
		// 根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		/**
		 * 可以从数据库查出来用户名和密码进行比对,为了方便我这里就直接固定了
		 */
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码是:"+password);
		return new SocialUser(userId, password,
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
}
```

### 4.Session集群测试

​	本处可用的存取Session的方式有很多,该项目使用常见的菲关系型数据库Redis来做相应的集群环境下的session的存储,主要配置:

```java
.sessionManagement()
.invalidSessionUrl(securityProperties.getBrower().getSession().getSessionInvalidUrl())//session失效的地址
 .maximumSessions(securityProperties.getBrower().getSession().getMaximumSessions()) //设置session的最大数量 按用户名来判断的
  .maxSessionsPreventsLogin(true)//当达到session的最大数量时候阻止其他的登录,即踢下线
  .expiredSessionStrategy(new CityExpiredSessionStrategy())
```

application.properties:

```properties
#这里是单机session的配置
#最大的登录session数量
city.security.brower.session.maximumSessions= 2
#超出最大的登录session数量的跳转提示页面
#city.security.brower.session.maxSessionsPreventsLogin= true
#session失效的页面
city.security.brower.session.sessionInvalidUrl= /page/invalidSession  
#session的存储类型
spring.session.store-type=none
#spring.session.store-type=REDIS
```

这里需要注意的是在配置多Session集群的环境下请关闭图形验证码测试,因为BufferedImag类会报未序列化异常,建议后续改为纯字符串传输给前台。

### 5.社交登录演示

![uwt1Wd.gif](https://s2.ax1x.com/2019/10/03/uwt1Wd.gif)

### 6.项目git地址

<font color=#68228B  size=3>(喜欢记得点星支持哦,谢谢!)</font> 

<font color=#EEB422   size=4>[https://github.com/fengcharly/spring-security-oauth2.0](https://github.com/fengcharly/spring-security-oauth2.0)</font> 
