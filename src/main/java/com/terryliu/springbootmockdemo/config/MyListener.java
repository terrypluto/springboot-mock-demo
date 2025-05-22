package com.terryliu.springbootmockdemo.config;

import com.terryliu.springbootmockdemo.mapper.UserBaseMapper;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/3
 */
//@Component
public class MyListener implements ApplicationListener<RedisAsyncEvent> {
    private final RedisTemplate<String, String> redisTemplate;
    private final UserBaseMapper userBaseMapper;

    public MyListener(RedisTemplate<String, String> redisTemplate, UserBaseMapper userBaseMapper) {
        this.redisTemplate = redisTemplate;
        this.userBaseMapper = userBaseMapper;
    }

    @Override
    public void onApplicationEvent(RedisAsyncEvent event) {
        System.out.println("已同步");
    }
}
