package com.terryliu.springbootmockdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/5
 */
//@Configuration
public class RedisConfig {
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory, RedisMessageListener redisListener,
            Redis2MessageListener redisListener2) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        //订阅topic - subscribe
        ChannelTopic channelTopic = new ChannelTopic("test:message");
        redisMessageListenerContainer.addMessageListener(redisListener,channelTopic);
        redisMessageListenerContainer.addMessageListener(redisListener2,channelTopic);
        return redisMessageListenerContainer;
    }
}
