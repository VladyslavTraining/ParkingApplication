package com.delphi.nice.training.model.dto;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ClientCardDto {

    private String licencePlate;
    private int parkingCardNumber;

}
