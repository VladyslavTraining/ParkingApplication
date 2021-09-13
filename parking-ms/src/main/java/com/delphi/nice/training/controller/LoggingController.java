package com.delphi.nice.training.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Slf4j
public class LoggingController {
    public static List<String> VALID_LEVELS = Arrays.asList("TRACE", "DEBUG", "INFO", "WARN", "ERROR");

    public void setLogLevel(String logLevel) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger("com.delphi.nice.training.service");
        System.out.println("com.delphi.nice.training.service" + " current logger level: " + logger.getLevel());
        System.out.println(" You entered: " + logLevel);

        logger.setLevel(Level.toLevel(logLevel));
    }

    public static void changeLogLevel() {
        LoggingController demo = new LoggingController();
        Scanner scanner = new Scanner(System.in);
        String updateLogLevel;
        do {
            System.out.println("Enter Logback Logger Level - TRACE, DEBUG, INFO, WARN, ERROR! ");
            System.out.println("q to quit, anything else to repeat this message: ");
            updateLogLevel = scanner.nextLine();
            if (LoggingController.VALID_LEVELS.contains(updateLogLevel)) {
                demo.setLogLevel(updateLogLevel);
            }

        } while (!updateLogLevel.equalsIgnoreCase("q"));

        scanner.close();
    }

}
