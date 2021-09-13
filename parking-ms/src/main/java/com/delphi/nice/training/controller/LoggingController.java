package com.delphi.nice.training.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoggingController {

    @GetMapping("admin/log/{logLevel}")
    public void setLogLevel(@PathVariable String logLevel) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("com.delphi.nice.training.service");
        System.out.println("com.delphi.nice.training.service" + " current logger level: " + logger.getLevel());
        System.out.println(" You entered: " + logLevel);
        logger.setLevel(Level.toLevel(logLevel));
    }


}
