package com.terryliu.springbootmockdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.terryliu.springbootmockdemo.util.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <h1>Copyright (C), 2022 ~ 2023, NyquistAi.inc</h1>
 * <p>Jackson test</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/23
 */
public class JacksonTest {
    @Test
    void object2Json() throws JsonProcessingException {
        User user = new User();
        user.setId(1);
        user.setAge(12);
        user.setName("Ari");
        user.setGrowState(0);
        user.setDate(new Date());
        user.setMap(Map.of("key", "value"));
        user.setAnimal(List.of(new Animal("nini")));

        String json = JSONUtil.toJson(List.of(user));
        System.out.println(json);
    }

    @Test
    void json2Object() throws JsonProcessingException {
        String json = """
               [{"id":1,"name":"Ari","age":12,"email":"","grow_state":0,"date":1714974428588,"map":{"key":"value"},"animal":[{"name":"nini"}]}]
               """;
        List<User> users = JSONUtil.fromJsonList(json, User.class);
        System.out.println(users);
    }

    @Test
    void json2Map() throws JsonProcessingException {
        String json = """
                {"id":1,"name":"Ari","age":12,"email":"","grow_state":0,"map":{"key":"value"},"animal":[{"name":"nini"}]}
                """;
        Map<String, Object> stringObjectHashMap = JSONUtil.fromJsonMap(json,String.class, Object.class);
        System.out.println(stringObjectHashMap);
    }

    @Data
    static
    class User {
        private Integer id;
        private String name;
        private int age;
        private String email;
        private int growState;
        private Date date;
        private Map<String,String> map;
        private List<Animal> animal;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Animal{
        private String name;
    }

    @Test
    public void translate(){
        PropertyNamingStrategies.NamingBase namingBase = new PropertyNamingStrategies.SnakeCaseStrategy();
        String translate = namingBase.translate("GrowStA_Te");
        System.out.println(translate);

        
    }
}
