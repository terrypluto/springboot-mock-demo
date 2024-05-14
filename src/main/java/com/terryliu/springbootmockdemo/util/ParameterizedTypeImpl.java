package com.terryliu.springbootmockdemo.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * <p>type and class</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/24
 */
@EqualsAndHashCode
public class ParameterizedTypeImpl implements ParameterizedType {
    private final Type[] actualTypeArguments;
    @Getter
    private final Class<?>  rawType;
    @Getter
    private final Type ownerType;

    private ParameterizedTypeImpl(Class<?> rawType,
                                  Type[] actualTypeArguments,
                                  Type ownerType) {
        this.actualTypeArguments = actualTypeArguments;
        this.rawType             = rawType;
        this.ownerType = (ownerType != null) ? ownerType : rawType.getDeclaringClass();
    }


    public static ParameterizedTypeImpl make(Class<?> rawType,
                                             Type[] actualTypeArguments,
                                             Type ownerType) {
        return new ParameterizedTypeImpl(rawType, actualTypeArguments,
                ownerType);
    }

    public Type[] getActualTypeArguments() {
        return actualTypeArguments.clone();
    }
}
