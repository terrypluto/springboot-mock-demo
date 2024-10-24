package com.terryliu.springbootmockdemo.service;

import com.terryliu.springbootmockdemo.SpringbootMockDemoApplicationTests;
import com.terryliu.springbootmockdemo.mapper.UserBase;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>Copyright (C), 2022 ~ 2023, NyquistAi.inc</h1>
 * <p>测试{@link org.springframework.boot.test.mock.mockito.MockBean MockBean}</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
public class UserServiceTest extends SpringbootMockDemoApplicationTests {
    @Resource
    private UserService userService;
    @MockBean
    private FakeService fakeService;

    @BeforeEach
    void setUp(){
        Mockito.when(fakeService.add(Mockito.anyInt(),Mockito.anyInt())).thenReturn(5);
    }

    @Test
    void testFake(){
        Integer i = userService.get();
        System.out.println(i);
    }

    @Test
    void test(){
        List<UserBase> userBases = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            UserBase userBase = new UserBase()
                    .setGender("男")
                    .setUsername("test"+i)
                    .setState((short) 0);
            userBases.add(userBase);
        }
        userService.batch(userBases);
    }
}
