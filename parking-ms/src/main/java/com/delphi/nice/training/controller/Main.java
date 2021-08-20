package com.delphi.nice.training.controller;

import com.delphi.nice.training.service.ExitService;
import com.delphi.nice.training.service.TicketService;


public class Main {
    public static void main(String[] args) {
        new TicketService().generateTicket();
        new ExitService().exit(1629457601949L);
    }


}

