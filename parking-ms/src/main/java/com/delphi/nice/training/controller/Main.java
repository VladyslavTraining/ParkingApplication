package com.delphi.nice.training.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {
//    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        IntroduceServiceImpl intro = context.getBean("introduceServiceImpl", IntroduceServiceImpl.class);
//        intro.welcomeMessage();
            SpringApplication.run(Main.class, args);
    }
}

