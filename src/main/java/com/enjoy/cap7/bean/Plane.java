package com.enjoy.cap7.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Plane implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Plane() {
        System.out.println("Plane......construct");

    }

    @PostConstruct
    public void init() {
        System.out.println("Plane...... @PostConstruct");

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Plane...... @PreDestroy");

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //将applicationContext拿进来
        this.applicationContext = applicationContext;
    }
}
