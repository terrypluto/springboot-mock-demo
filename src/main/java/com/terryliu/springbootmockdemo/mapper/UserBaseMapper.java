package com.terryliu.springbootmockdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.terryliu.springbootmockdemo.mapper.UserBase;
import org.apache.ibatis.annotations.Mapper;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 * 
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since  2024/4/19
 */
@Mapper
public interface UserBaseMapper extends BaseMapper<UserBase> {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserBase record);

    UserBase selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBase record);
}