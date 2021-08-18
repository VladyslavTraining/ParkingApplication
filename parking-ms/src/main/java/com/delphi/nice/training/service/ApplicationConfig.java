package com.delphi.nice.training.service;

import com.delphi.nice.training.model.TicketDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {
//    @Bean
//    @Scope("Prototype")
//    public ClientDto clientDto() {
//        return new ClientDto();
//    }

    @Bean
    @Scope("Prototype")
    public TicketDto ticketDto() {
        return new TicketDto();
    }
}
