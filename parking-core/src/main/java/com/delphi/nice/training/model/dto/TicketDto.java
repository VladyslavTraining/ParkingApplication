package com.delphi.nice.training.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDto {

    private long uuid;
    private LocalDateTime entranceDateTime;

    public TicketDto() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = System.currentTimeMillis() >> 23;
    }

}

