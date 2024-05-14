package com.terryliu.springbootmockdemo.service.impl;

import com.terryliu.springbootmockdemo.mapper.UserBase;
import com.terryliu.springbootmockdemo.mapper.UserBaseMapper;
import com.terryliu.springbootmockdemo.service.FakeService;
import com.terryliu.springbootmockdemo.service.UserService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.random.RandomGenerator;

/**
 * Copyright (C), 2022 ~ 2023, NyquistAi.inc
 * todo
 *
 * @author Nyquist Data Tech Team
 * @version 1.0.0
 * @since 2024/4/19
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String[] GENDERS = {"male", "female"};
    private final UserBaseMapper userBaseMapper;
    private final FakeService fakeService;
    /**{@inheritDoc}*/
    @Override
    public Integer create() {
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        int i = randomGenerator.nextInt(2);
        UserBase userBase = new UserBase();
        userBase.setState((short) 0);
        userBase.setGender(GENDERS[i]);
        userBaseMapper.insert(userBase);
        return userBase.getId();
    }

    @Override
    public Integer get(){
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        return fakeService.add(randomGenerator.nextInt(100),randomGenerator.nextInt(100));
    }

    @Override
    public void download() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getResponse();
        setRespHeader(response,"user.txt", MediaType.TEXT_HTML_VALUE);
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            String text = "subject";
            outputStream.write(text.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    private static void setRespHeader(HttpServletResponse response, String fileName, String contentType) {
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        response.setContentType(contentType + ";charset=UTF-8");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, HttpHeaders.CONTENT_DISPOSITION);
        response.setDateHeader(HttpHeaders.EXPIRES, 0);
    }
}
