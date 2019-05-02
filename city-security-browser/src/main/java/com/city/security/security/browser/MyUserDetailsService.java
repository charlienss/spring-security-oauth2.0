//package com.city.security.security.browser;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.social.security.SocialUser;
//import org.springframework.social.security.SocialUserDetails;
//import org.springframework.social.security.SocialUserDetailsService;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//    //每次加密的结果都是不一样的
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        //根据用户名查找用户信息
//        logger.info("表单登录用户名:" + username);
//        return buildUser(username);
//    }
//
//
//    //这个是第三方登录的 社交登录
//    @Override
//    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
//        //根据用户名查找用户信息
//        logger.info("社交登录userId:" + userId);
//        return buildUser(userId);
//    }
//
//
//    private SocialUserDetails buildUser(String userId) {
//
//        String encodePassword = passwordEncoder.encode("123456");
//        logger.info("数据库密码是:" + encodePassword);
//        // System.out.println("encodePassword=>"+encodePassword);
//        //最后一个属性来进行授权
//        return new SocialUser(userId, encodePassword,
//                true, true, true, true,
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));//权限
//    }
//}
