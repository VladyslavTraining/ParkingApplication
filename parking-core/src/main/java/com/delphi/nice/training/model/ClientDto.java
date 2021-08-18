package com.delphi.nice.training.model;

import lombok.Data;
import org.springframework.stereotype.Component;
@Component("client")
@Data
public class ClientDto {

    private String licencePlate;
    private long cardNumber;

//    public ClientDto(String licencePlate, long cardNumber) {
//        this.licencePlate = licencePlate;
//        this.cardNumber = cardNumber;
//    }



}
