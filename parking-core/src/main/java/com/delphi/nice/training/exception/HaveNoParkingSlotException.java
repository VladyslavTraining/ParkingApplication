package com.delphi.nice.training.exception;

public class HaveNoParkingSlotException extends RuntimeException {
    public HaveNoParkingSlotException() {
        super("Parking area is already full");
    }
}
