package com.delphi.nice.training.controller;

import com.delphi.nice.training.configuration.ApplicationConfig;
import com.delphi.nice.training.model.dto.ClientCardDto;
import com.delphi.nice.training.model.dto.TicketDto;
import com.delphi.nice.training.service.IntroduceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        TicketDto dto = context.getBean("ticket", TicketDto.class);
        TicketDto dto1 = context.getBean("ticket", TicketDto.class);
        TicketDto dto2 = context.getBean("ticket", TicketDto.class);
        System.out.println(dto.getUuid());
        System.out.println(dto1.getUuid());
        System.out.println(dto2.getUuid());
    }


}

