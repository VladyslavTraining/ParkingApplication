package com.delphi.nice.training.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext app = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        ClientDto clientDto = app.getBean(ClientDto.class);
//        clientDto.setCardNumber(123L);
//        clientDto.setLicencePlate("AB123AB");
//        System.out.println(clientDto);

        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        ClientDto clientDto = app.getBean("client",ClientDto.class);
        clientDto.setCardNumber(123L);
        clientDto.setLicencePlate("AB123AB");
        System.out.println(clientDto);
    }
}
