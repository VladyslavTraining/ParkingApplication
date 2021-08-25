package com.delphi.nice.training.configuration;

import com.delphi.nice.training.model.dto.TicketDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;


@Configuration
public class ApplicationConfig {

    @Bean
    @Scope("prototype")
    public TicketDto ticket() {
        return new TicketDto();
    }

}
