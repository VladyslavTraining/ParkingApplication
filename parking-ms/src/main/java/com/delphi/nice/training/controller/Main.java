package com.delphi.nice.training.controller;

import com.delphi.nice.training.service.TicketService;


public class Main {
    public static void main(String[] args) {
        for(int i = 0;i<20;i++) {
            System.out.println(new TicketService().generateTicket());
        }
    }


}

