package com.terryliu.springbootmockdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.info.OsInfo;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.Objects;

@SpringBootTest
@Slf4j
public class SpringbootMockDemoApplicationTests {
    protected static String local;

    @BeforeAll
    static void init(){
        OsInfo osInfo = new OsInfo();
        String name = osInfo.getName().toLowerCase();
        if(name.contains("windows")){
            local = System.getProperty("user.home") + File.separator + "Downloads" + File.separator;
        }else {
            local = System.getProperty("user.home") + File.separator;
        }
        log.info("文件下载位置:{}",local);
    }

    @Test
    void contextLoads() {
    }

}
