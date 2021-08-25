package com.delphi.nice.training.model.dto;

import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@ToString
public class TicketDto {

    private long uuid;
    private LocalDateTime entranceDateTime;

    public TicketDto() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = System.currentTimeMillis()>>22;
    }

}

