package com.terryliu.springbootmockdemo.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/6/4
 */
//@Component
public class CustomLifeCycle implements SmartLifecycle {
    private final ApplicationContext applicationContext;
    public CustomLifeCycle(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
    private final AtomicBoolean  runningFlag = new AtomicBoolean(false);

    @Override
    public void start() {
        if(runningFlag.compareAndSet(false,true)){
            applicationContext.publishEvent(new RedisAsyncEvent(new Object()));
            System.out.println("start");
        }
    }

    @Override
    public void stop() {
        if(runningFlag.compareAndSet(true,false)){
            System.out.println("stop");
        }
    }

    @Override
    public boolean isRunning() {
        return runningFlag.get();
    }
}
