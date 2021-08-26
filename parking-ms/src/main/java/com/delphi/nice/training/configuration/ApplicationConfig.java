package com.delphi.nice.training.configuration;

import com.delphi.nice.training.model.dto.TicketDto;
import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.IntroduceService;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class ApplicationConfig {

    @Bean
    public TicketService ticketService() {
        return new TicketService(ticketDto());
    }

    @Bean
    @Scope("prototype")
    public TicketDto ticketDto() {
        return new TicketDto();
    }

    @Bean
    public ParkingService parkingService() {
        return new ParkingService();
    }

    @Bean
    public ExitService exitService() {
        return new ExitService(parkingService(), ticketService());
    }

    @Bean
    public IntroduceService intro() {
        return new IntroduceService(ticketService(), exitService());
    }

}
