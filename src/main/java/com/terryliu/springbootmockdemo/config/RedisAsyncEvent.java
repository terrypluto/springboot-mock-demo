package com.terryliu.springbootmockdemo.config;

import org.springframework.context.ApplicationEvent;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/3
 */
public class RedisAsyncEvent extends ApplicationEvent {
    public RedisAsyncEvent(Object source) {
        super(source);
    }
}
