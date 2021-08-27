package com.delphi.nice.training.controller;

import com.delphi.nice.training.configuration.ApplicationConfig;
import com.delphi.nice.training.service.IntroduceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {


    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IntroduceService intro = context.getBean("intro", IntroduceService.class);
        intro.welcomeMessage();
    }

}

