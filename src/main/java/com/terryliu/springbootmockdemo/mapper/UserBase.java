package com.terryliu.springbootmockdemo.mapper;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * description
 *
 * @author Terry.Liu
 * @version 1.0.0
 * @since 2025/5/9
 */

@Data
@Accessors(chain = true)
@TableName(value = "user_base", autoResultMap = true)
public class UserBase {
    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;
    
    @TableField(value = "username")
    private String username;
    
    @TableField(value = "gender")
    private String gender;
    
    @TableField(value = "\"state\"")
    private Object state;
    
    @TableField(value = "information",typeHandler = JsonToListTypeHandler.class)
    private List<Information> information;
}