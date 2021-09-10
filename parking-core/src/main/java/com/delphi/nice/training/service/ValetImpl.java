package com.delphi.nice.training.service;

import org.springframework.stereotype.Service;

@Service
public class ValetImpl implements Valet{
    ParkingService parkingService;
    ExitService exitService;

    public ValetImpl(ParkingService parkingService, ExitService exitService) {
        this.parkingService = parkingService;
        this.exitService = exitService;
    }

    @Override
    public void parkTheCar() {

    }

    @Override
    public void exitTheCar() {

    }

}
