package com.delphi.nice.training.service;

import com.delphi.nice.training.dto.TicketDto;

public interface Valet {
    TicketDto parkTheCar();
    String exitTheCar(long uuid);
    TicketDto getTicketById(long uuid);
}
