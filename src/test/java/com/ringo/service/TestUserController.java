package com.ringo.service;

import com.ringo.App;
import com.ringo.controller.UserController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {App.class})//加载配置类
@AutoConfigureMockMvc
public class TestUserController {
    @Resource
    private MockMvc mockMvc;
    private static Logger logger= LoggerFactory.getLogger(TestUserService.class);

    @Before
    public void before(){
        logger.info("执行之前");
    }
    @Test
    public void test() throws Exception{
        MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get("/user/get/1")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        logger.info("响应状态："+mvcResult.getResponse().getStatus());
        logger.info("获取内容："+mvcResult.getResponse().getContentAsString());
    }

    @After
    public void after(){
        logger.info("执行之后");
    }
}
