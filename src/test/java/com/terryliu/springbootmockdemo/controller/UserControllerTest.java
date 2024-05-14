package com.terryliu.springbootmockdemo.controller;

import com.terryliu.springbootmockdemo.SpringbootMockDemoApplicationTests;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * MockMvc Test
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
public class UserControllerTest extends SpringbootMockDemoApplicationTests {
    MockMvc mockMvc;
    @Resource
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testCreateUser() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        System.out.println(response.getContentAsString());
    }

    @Test
    void testDownload() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/user/download")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        response.setCharacterEncoding("UTF-8");
        Files.write(Paths.get(local + "user.txt"), response.getContentAsByteArray());
    }
}
