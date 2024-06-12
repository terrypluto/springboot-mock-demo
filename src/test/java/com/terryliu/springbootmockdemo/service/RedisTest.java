package com.terryliu.springbootmockdemo.service;

import com.terryliu.springbootmockdemo.SpringbootMockDemoApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/5
 */
public class RedisTest extends SpringbootMockDemoApplicationTests {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testRedis() throws InterruptedException {
        redisTemplate.convertAndSend("test:message","test");
        Thread.sleep(100000);
    }
}
