package com.terryliu.springbootmockdemo.mapper;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;

/**
 * description
 *
 * @author Terry.Liu
 * @version 1.0.0
 * @since 2025/5/9
 */
@Slf4j
@MappedTypes({Object.class})
@MappedJdbcTypes(JdbcType.VARCHAR)
public class JsonToListTypeHandler<T> extends AbstractJsonTypeHandler<T> {
    private static ObjectMapper OBJECT_MAPPER;
    public JsonToListTypeHandler(Class<?> type) {
       super(type);
    }
   
    public JsonToListTypeHandler(Class<?> type, Field field) {
       super(type, field);
    }
    
    @Override
    public T parse(String json) {
        ObjectMapper objectMapper = getObjectMapper();
        TypeFactory typeFactory = objectMapper.getTypeFactory();
        JavaType javaType = typeFactory.constructType(getFieldType());
        try {
            return objectMapper.readValue(json, javaType);
        } catch (JacksonException e) {
            log.error("deserialize json: " + json + " to " + javaType + " error ", e);
            throw new RuntimeException(e);
        }
    }
    @Override
    public String toJson(Object obj) {
        try {
            return getObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error("serialize " + obj + " to json error ", e);
            throw new RuntimeException(e);
        }
    }
    
    public static ObjectMapper getObjectMapper() {
        if (null == OBJECT_MAPPER) {
            OBJECT_MAPPER = new ObjectMapper();
        }
        return OBJECT_MAPPER;
    }
}
