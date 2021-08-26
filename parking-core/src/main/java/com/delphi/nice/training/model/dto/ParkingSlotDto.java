package com.delphi.nice.training.model.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Getter
public class ParkingSlotDto implements Serializable {
    private int parkingSlot;
    private boolean isParked;
}
