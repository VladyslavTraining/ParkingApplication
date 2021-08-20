package com.delphi.nice.training.controller;

public class OpenParking implements Command {

    private Gate gate;

    public OpenParking(Gate gate) {
        this.gate = gate;
    }

    @Override
    public void execute() {
        gate.openParkingDoor();
    }
}
