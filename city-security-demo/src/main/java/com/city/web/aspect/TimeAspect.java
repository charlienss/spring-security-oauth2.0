//package com.city.web.aspect;
//
//import com.navercorp.pinpoint.common.annotations.InterfaceAudience;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import java.security.PublicKey;
//import java.util.Date;
//
//@Aspect
//@Component
//public class TimeAspect {
//
//
//
//    @Around("execution(* com.city.DemoApplication.*(..))")
//    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
//
//        long start = new Date().getTime();
//        Object[] args = pjp.getArgs();
//        for (Object arg : args) {
//            System.out.println("arg is "+arg);
//        }
//
//        System.out.println("time aspect start");
//        Object object = pjp.proceed();
//
//        System.out.println("time aspect 耗时:"+(new Date().getTime()-start));
//
//        System.out.println("time aspect end");
//
//        return object;
//    }
//
//
//}
