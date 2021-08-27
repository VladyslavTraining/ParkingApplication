package com.delphi.nice.training.configuration;

import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.IntroduceService;
import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.TicketService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    @Bean
    public TicketService ticketService(ParkingService parkingService) {
        return new TicketService(parkingService);
    }

    @Bean
    public ParkingService parkingService() {
        return new ParkingService();
    }

    @Bean
    public ExitService exitService(ParkingService parkingService, TicketService ticketService) {
        return new ExitService(parkingService, ticketService);
    }

    @Bean
    public IntroduceService intro(TicketService ticketService, ExitService exitService) {
        return new IntroduceService(ticketService, exitService);
    }

}
