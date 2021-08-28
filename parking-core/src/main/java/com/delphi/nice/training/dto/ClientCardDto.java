package com.delphi.nice.training.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClientCardDto {

    private String licencePlate;
    private int parkingCardNumber;

}
