package com.delphi.nice.training.controller;

import com.delphi.nice.training.configuration.ApplicationConfig;
import com.delphi.nice.training.service.IntroduceServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class Main {

    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IntroduceServiceImpl intro = context.getBean("introduceServiceImpl", IntroduceServiceImpl.class);
        intro.welcomeMessage();
    }

}

