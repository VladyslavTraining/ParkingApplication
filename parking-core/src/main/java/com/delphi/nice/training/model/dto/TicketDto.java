package com.delphi.nice.training.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class TicketDto implements Serializable {

    private long uuid;
    private LocalDateTime entranceDateTime;
//    private Card clientCard;
    public TicketDto() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = System.currentTimeMillis();
    }


}
