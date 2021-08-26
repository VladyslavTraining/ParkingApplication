package com.delphi.nice.training.controller;

import com.delphi.nice.training.configuration.ApplicationConfig;
import com.delphi.nice.training.service.IntroduceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    private static final Logger log = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        log.info("Start application");
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IntroduceService intro = context.getBean("intro", IntroduceService.class);
        intro.welcomeMessage();
        log.info("Application get end");
    }

}

