package com.delphi.nice.training.ticket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Setter
public class Ticket {
    private long uuid;
    private LocalDateTime entranceDateTime;
    private boolean isValid;

    public Ticket() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = entranceDateTime.getNano() >> 8;
        this.isValid = true;
    }
}

