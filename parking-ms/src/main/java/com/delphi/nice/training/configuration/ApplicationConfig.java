package com.delphi.nice.training.configuration;

import com.delphi.nice.training.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    @Bean
    public TicketService ticketService() {
        return new TicketServiceImpl(parkingService());
    }

    @Bean
    public ParkingService parkingService() {
        return new ParkingServiceImpl();
    }

    @Bean
    public ExitService exitService() {
        return new ExitServiceImpl();
    }

    @Bean
    public IntroduceService intro() {
        return new IntroduceServiceImpl(ticketService(), exitService());
    }

}
