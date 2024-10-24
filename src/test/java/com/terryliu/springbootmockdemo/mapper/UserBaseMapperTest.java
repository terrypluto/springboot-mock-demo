package com.terryliu.springbootmockdemo.mapper;


import com.terryliu.springbootmockdemo.SpringbootMockDemoApplicationTests;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;
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
public class UserBaseMapperTest extends SpringbootMockDemoApplicationTests {
    private static final String[] GENDERS = {"male", "female"};
    @Resource
    UserBaseMapper userBaseMapper;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Test
    void testInsert(){
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        int i = randomGenerator.nextInt(2);
        UserBase userBase = new UserBase();
        userBase.setState((short) 0);
        userBase.setGender(GENDERS[i]);
        userBaseMapper.insert(userBase);
    }


    @Test
    void batch(){
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH);
        UserBaseMapper mapper = sqlSession.getMapper(UserBaseMapper.class);
        RandomGenerator randomGenerator = RandomGenerator.getDefault();
        try {
            for (int i = 0; i < 10; i++) {
                UserBase userBase = new UserBase()
                        .setGender(GENDERS[randomGenerator.nextInt(2)])
                        .setUsername("test"+i)
                        .setState((short) 0);
                mapper.insert(userBase);
                if(i==5 || i == 9){
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }
}
