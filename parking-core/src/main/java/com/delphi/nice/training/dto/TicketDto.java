package com.delphi.nice.training.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Getter
@ToString
public class TicketDto {

    private long uuid;
    private LocalDateTime entranceDateTime;

    public TicketDto() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = entranceDateTime.getNano()>>8;
    }

}

