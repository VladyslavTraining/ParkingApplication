package com.delphi.nice.training.configuration;

import com.delphi.nice.training.service.ExitServiceImpl;
import com.delphi.nice.training.service.IntroduceServiceImpl;
import com.delphi.nice.training.service.ParkingServiceImpl;
import com.delphi.nice.training.service.TicketServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfig {

    @Bean
    public TicketServiceImpl ticketService(ParkingServiceImpl parkingServiceImpl) {
        return new TicketServiceImpl(parkingServiceImpl);
    }

    @Bean
    public ParkingServiceImpl parkingService() {
        return new ParkingServiceImpl();
    }

    @Bean
    public ExitServiceImpl exitService(ParkingServiceImpl parkingServiceImpl, TicketServiceImpl ticketServiceImpl) {
        return new ExitServiceImpl(parkingServiceImpl, ticketServiceImpl);
    }

    @Bean
    public IntroduceServiceImpl intro(TicketServiceImpl ticketServiceImpl, ExitServiceImpl exitServiceImpl) {
        return new IntroduceServiceImpl(ticketServiceImpl, exitServiceImpl);
    }

}
