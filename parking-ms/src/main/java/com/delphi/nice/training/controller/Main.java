package com.delphi.nice.training.controller;

import com.delphi.nice.training.dto.TicketDto;
import com.delphi.nice.training.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@SpringBootApplication
//@RestController
@ComponentScan("com/delphi/nice/training/service")
@PropertySource("classpath:application.properties")
public class Main {
//    static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        IntroduceServiceImpl intro = context.getBean("introduceServiceImpl", IntroduceServiceImpl.class);
//        intro.welcomeMessage();
        SpringApplication.run(Main.class, args);
    }

//    TicketService ticketService;

    //
//    @Autowired
//    public Main(TicketService ticketService) {
//        this.ticketService = ticketService;
//    }
//
//    @PostMapping
//    public void registerNewTicket() {
//        ticketService.generateTicket();
//    }

//    @GetMapping
//    public String defaultPage() {
//        return "default page";
//    }
//
//    @GetMapping("/hello")
//    public List<TicketDto> getTickets() {
//        return ticketService.getAllTickets();
//    }
}

