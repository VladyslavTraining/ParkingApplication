package com.delphi.nice.training.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component("client")
@Data
public class ParkingCardDto {

    private String licencePlate;
    private long parkingCardNumber;

}
