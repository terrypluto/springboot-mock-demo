package com.terryliu.springbootmockdemo.annotation;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/6
 */
//@Configuration
public class DemoAopConfig {
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(new AnnotationMatchingPointcut(null,UserLog.class,true));
        defaultPointcutAdvisor.setAdvice(new UserLogInterceptor());
        return defaultPointcutAdvisor;
    }
}
