package com.delphi.nice.training.ticket;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@Setter
@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long uuid;
    private LocalDateTime entranceDateTime;
    private boolean isValid;

    public Ticket() {
        this.entranceDateTime = LocalDateTime.now();
        this.uuid = entranceDateTime.getNano() >> 8;
        this.isValid = true;
    }
}

