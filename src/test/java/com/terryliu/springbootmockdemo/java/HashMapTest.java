package com.terryliu.springbootmockdemo.java;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/27
 */
public class HashMapTest {
    @Test
    public void test1(){
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("key1", "value1");
    }
}
