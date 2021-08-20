package com.delphi.nice.training.controller;


public class Main {

    public static void main(String[] args) {
        Gate gate = new Gate();

        Command switchUp = new OpenParking(gate);
        Command switchDown = new CloseParking(gate);

        Manager manager = new Manager(switchUp, switchDown);

        manager.openParking();
        manager.closeParking();
    }

}

