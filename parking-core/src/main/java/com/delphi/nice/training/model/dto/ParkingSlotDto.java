package com.delphi.nice.training.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ParkingSlotDto implements Serializable {
    private int parkingSpot;
    private boolean isParked;
}
