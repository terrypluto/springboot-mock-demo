package com.terryliu.springbootmockdemo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/4
 */
//@Component
public class MyProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization==> " + beanName);
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization==> " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
