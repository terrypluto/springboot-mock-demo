package com.terryliu.springbootmockdemo.service;

import com.terryliu.springbootmockdemo.SpringbootMockDemoApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/22
 */
public class ThreadSafeServiceTest extends SpringbootMockDemoApplicationTests {
    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    @Resource
    protected ThreadSafeService threadSafeService;

    @Test
    public void testThreadSafeService() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {

            int finalI = i;

            threadPool.submit(new Runnable() {

                @Override

                public void run() {

                    String date = threadSafeService.date(finalI);

                    System.out.println(date);

                }

            });



        }
        Thread.sleep(2000);
    }
}
