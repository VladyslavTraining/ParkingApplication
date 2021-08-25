package com.delphi.nice.training.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Data
public class ParkingSlotDto implements Serializable {
    private int parkingSpot;
    private boolean isParked;
}
