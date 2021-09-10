package com.delphi.nice.training.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class TicketDto {

    private long uuid;
    private LocalDateTime entranceDateTime;

    public TicketDto() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = entranceDateTime.getNano()>>8;
    }

}

