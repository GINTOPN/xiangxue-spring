package com.enjoy.cap9.config;

import com.enjoy.cap9.dao.TestDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan({"com.enjoy.cap9.controller","com.enjoy.cap9.service","com.enjoy.cap9.dao"})
public class Cap9MainConfig {

//    @Primary  //spring默认装配时默认首选的bean
    @Bean("testDao2")
    public TestDao testDao(){
        TestDao testDao = new TestDao();
        testDao.setFlag("2");
        return testDao;
    }
}