package com.terryliu.springbootmockdemo.service.impl;

import com.terryliu.springbootmockdemo.service.FakeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
@Slf4j
@Service
public class FakeServiceImpl implements FakeService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }
}
