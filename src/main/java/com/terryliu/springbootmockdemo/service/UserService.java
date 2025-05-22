package com.terryliu.springbootmockdemo.service;

import com.terryliu.springbootmockdemo.annotation.UserLog;
import com.terryliu.springbootmockdemo.mapper.UserBase;

import java.util.List;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
public interface UserService  {
    Integer create();
    @UserLog
    Integer get();

    void download();


    void batch(List<UserBase> userBases);
    
    void batchSaveOrUpdate(List<UserBase> userBases);
}
