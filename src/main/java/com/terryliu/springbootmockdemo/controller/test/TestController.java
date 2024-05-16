package com.terryliu.springbootmockdemo.controller.test;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>todo</p>
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/5/16
 */
@RestController
@RequestMapping("test")
@AllArgsConstructor
public class TestController {
    @PostMapping("create")
    public ResponseEntity<Integer> createUser() {
        throw new RuntimeException();
//        return ResponseEntity.ok(userService.create());
    }
}
