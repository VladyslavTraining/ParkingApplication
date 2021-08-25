package com.delphi.nice.training.controller;

import com.delphi.nice.training.configuration.ApplicationConfig;
import com.delphi.nice.training.model.dto.TicketDto;
import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.TicketService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        TicketService ticketService = context.getBean("ticketService", TicketService.class);
        ExitService exitService = context.getBean("exitService", ExitService.class);
//        ticketService.generateTicket();
//        System.out.print(ticketService.getTicket());
        exitService.exit(388595L);
    }


}

