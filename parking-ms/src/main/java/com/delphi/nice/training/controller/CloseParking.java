package com.delphi.nice.training.controller;

public class CloseParking implements Command {

    private Gate gate;

    public CloseParking(Gate gate) {
        this.gate = gate;
    }

    @Override
    public void execute() {
        gate.closeParkingDoor();
    }

}
