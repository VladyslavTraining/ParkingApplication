package com.delphi.nice.training.exception;

public class TicketIsNotValidException extends RuntimeException {
    public TicketIsNotValidException(long uuid) {
        super("Ticket with uuid = " + uuid + " is not valid already");
    }
}
