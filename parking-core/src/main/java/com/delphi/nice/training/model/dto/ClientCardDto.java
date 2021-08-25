package com.delphi.nice.training.model.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ClientCardDto {

    private String licencePlate;
    private int parkingCardNumber;

    public ClientCardDto() {
    }

}
