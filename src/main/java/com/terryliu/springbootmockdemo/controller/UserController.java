package com.terryliu.springbootmockdemo.controller;

import com.terryliu.springbootmockdemo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("create")
    public ResponseEntity<Integer> createUser() {
        return ResponseEntity.ok(userService.create());
    }

    @PostMapping("download")
    public void download(){
        userService.download();
    }
}
