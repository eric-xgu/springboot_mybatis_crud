package com.ringo.service;

import com.ringo.App;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})//加载配置类
public class TestUserService {
    @Resource
    private UserService userService;
    private static Logger logger= LoggerFactory.getLogger(TestUserService.class);

    @Before
    public void before(){
        logger.info("执行之前");
    }

    @Test
    public void test(){
        logger.info("用户记录:"+userService.queryUserByUsername("eric"));
    }

    @After
    public void after(){
        logger.info("执行之后");
    }
}
