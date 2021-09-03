package com.delphi.nice.training.controller;

import com.delphi.nice.training.configuration.ApplicationConfig;
import com.delphi.nice.training.service.IntroduceServiceImpl;

import lombok.extern.slf4j.Slf4j;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@Slf4j
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IntroduceServiceImpl intro = context.getBean("introduceServiceImpl", IntroduceServiceImpl.class);
        intro.welcomeMessage();
    }

}

