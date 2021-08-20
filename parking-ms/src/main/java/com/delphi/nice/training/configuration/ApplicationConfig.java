package com.delphi.nice.training.configuration;

import com.delphi.nice.training.model.dto.TicketDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfig {
    @Bean
    @Scope("Prototype")
    public TicketDto ticketDto() {
        return new TicketDto();
    }
}
