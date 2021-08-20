package com.delphi.nice.training.controller;

public class Manager {

    private Command up;
    private Command down;

    public Manager(Command up, Command down) {
        this.up = up;
        this.down = down;
    }

    public void openParking() {
        up.execute();
    }

    public void closeParking() {
        down.execute();
    }

}
