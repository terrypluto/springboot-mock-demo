package com.terryliu.springbootmockdemo.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 * 
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since  2024/4/19
 */
@Data
@TableName("user_base")
public class UserBase {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String gender;

    private Short state;
}