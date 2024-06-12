package com.terryliu.springbootmockdemo.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/4
 */
@Aspect
@Component
public class DemoAop {
//    @Pointcut("@annotation(com.terryliu.springbootmockdemo.annotation.UserLog)")
    @Pointcut("@within(com.terryliu.springbootmockdemo.annotation.UserLog)")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("test");
    }
}
