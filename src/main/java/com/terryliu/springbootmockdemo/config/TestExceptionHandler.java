package com.terryliu.springbootmockdemo.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/16
 */
@RestControllerAdvice(value = "com.terryliu.springbootmockdemo.controller.test")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TestExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e) {
        return new ResponseEntity<>("test", HttpStatusCode.valueOf(500));
    }
}
