package com.delphi.nice.training.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class Main {
//    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        IntroduceServiceImpl intro = context.getBean("introduceServiceImpl", IntroduceServiceImpl.class);
//        intro.welcomeMessage();
        SpringApplication.run(Main.class, args);
    }
    @GetMapping("/hello")
    public String hello() {
        return "hello world!!!";
    }
}

