package com.terryliu.springbootmockdemo.mapper;


import com.terryliu.springbootmockdemo.SpringbootMockDemoApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.random.RandomGenerator;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
public class UserBaseMapperTest extends SpringbootMockDemoApplicationTests {
    private static final String[] GENDERS = {"male", "female"};
    @Resource
    UserBaseMapper userBaseMapper;

    @Test
    void testInsert(){
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        int i = randomGenerator.nextInt(2);
        UserBase userBase = new UserBase();
        userBase.setState((short) 0);
        userBase.setGender(GENDERS[i]);
        userBaseMapper.insertSelective(userBase);
    }
}
