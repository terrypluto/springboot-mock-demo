package com.terryliu.springbootmockdemo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * <p>jackson json util</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/24
 */
@Slf4j
public abstract class JSONUtil {
    private final static String JSON_OBJECT = "{}";
    private final static String DEFAULT_STRING = "";
    private final static int DEFAULT_NUMBER = 0;
    private final static boolean DEFAULT_BOOLEAN = false;

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializerFactory(objectMapper.getSerializerFactory().withSerializerModifier(new MyBeanSerializerModifier()));
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);
    }

    public static ObjectMapper getInstance() {
        return objectMapper;
    }

    /**
     * 将实体对象转换成json
     *
     * @param object 实体对象
     * @return JSON String
     */
    public static String toJson(Object object) {
        if (Objects.isNull(object)) {
            return JSON_OBJECT;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("{} to string error:", object, e);
            return JSON_OBJECT;
        }
    }


    public static <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.hasLength(json)) {
            try {
                return objectMapper.readValue(json, clazz);
            } catch (JsonProcessingException e) {
                log.error("{} to object error:", json, e);
            }
        }
        return null;
    }


    public static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        if (StringUtils.hasLength(json)) {
            try {
                return objectMapper.readValue(json, new TypeReference<>() {
                    @Override
                    public Type getType() {
                        return ParameterizedTypeImpl.make((Class<?>) ((ParameterizedType) super.getType()).getRawType(), new Type[]{clazz}, null);
                    }
                });
            } catch (JsonProcessingException e) {
                log.error("{} to list error:", json, e);
            }
        }
        return null;
    }


    public static <K, V> Map<K, V> fromJsonMap(String json, Class<K> keyClass, Class<V> valueClass) {
        if (StringUtils.hasLength(json)) {
            try {
                return objectMapper.readValue(json, new TypeReference<>() {
                    @Override
                    public Type getType() {
                        return ParameterizedTypeImpl.make((Class<?>) ((ParameterizedType) super.getType()).getRawType(), new Type[]{keyClass, valueClass}, null);
                    }
                });
            } catch (JsonProcessingException e) {
                log.error("{} to map error:", json, e);
            }
        }
        return null;
    }

    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        if (StringUtils.hasLength(json)) {
            try {
                return objectMapper.readValue(json, typeReference);
            } catch (JsonProcessingException e) {
                log.error("{} to T error:", json, e);
            }
        }
        return null;
    }


    static class MyBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            //循环所有的beanPropertyWriter
            for (BeanPropertyWriter beanProperty : beanProperties) {
                //判断字段的类型，如果是array，list，set则注册nullSerializer
                if (isArrayType(beanProperty)) {
                    //给writer注册一个自己的nullSerializer
                    beanProperty.assignNullSerializer(new NullArrayJsonSerializer());
                } else if (isNumberType(beanProperty)) {
                    beanProperty.assignNullSerializer(new NullNumberJsonSerializer());
                } else if (isBooleanType(beanProperty)) {
                    beanProperty.assignNullSerializer(new NullBooleanJsonSerializer());
                } else if (isStringType(beanProperty)) {
                    beanProperty.assignNullSerializer(new NullStringJsonSerializer());
                } else {
                    beanProperty.assignNullSerializer(new NullObjectJsonSerializer());
                }
            }
            return beanProperties;
        }

        /**
         * 是否是数组
         */
        private boolean isArrayType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是string
         */
        private boolean isStringType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
        }


        /**
         * 是否是int
         */
        private boolean isNumberType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return Number.class.isAssignableFrom(clazz);
        }

        /**
         * 是否是boolean
         */
        private boolean isBooleanType(BeanPropertyWriter writer) {
            Class<?> clazz = writer.getType().getRawClass();
            return clazz.equals(Boolean.class);
        }

    }

    /**
     * 处理数组类型的null值
     */
    static class NullArrayJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value == null) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        }
    }


    /**
     * 处理字符串等类型的null值
     */
    static class NullStringJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeString(DEFAULT_STRING);
        }
    }

    /**
     * 处理字符串等类型的null值
     */
    static class NullNumberJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeNumber(DEFAULT_NUMBER);
        }
    }

    /**
     * 处理字符串等类型的null值
     */
    static class NullBooleanJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            jsonGenerator.writeBoolean(DEFAULT_BOOLEAN);
        }
    }

    /**
     * 处理数组类型的null值
     */
    static class NullObjectJsonSerializer extends JsonSerializer<Object> {

        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value == null) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }
    }
}
