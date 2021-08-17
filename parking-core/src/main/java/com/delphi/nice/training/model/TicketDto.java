package com.delphi.nice.training.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class TicketDto implements Serializable {
    private ClientDto client;
    private long uuid;
    private LocalDateTime entranceDateTime;

    public TicketDto(ClientDto client, LocalDateTime entranceDateTime) {
        this.client = client;
        this.entranceDateTime = entranceDateTime;
        this.uuid = System.currentTimeMillis()<<7;
    }


}
