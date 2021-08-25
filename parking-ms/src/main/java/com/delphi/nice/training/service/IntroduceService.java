package com.delphi.nice.training.service;


import com.delphi.nice.training.model.dto.TicketDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntroduceService {

    public static void introduceMessage() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Hello, do you want stay at parking area?(Y/N)");
                String line = br.readLine();
                if (line.equalsIgnoreCase("Y")) {
                    TicketService ticketService = new TicketService();
                    ticketService.generateTicket();
                    TicketDto ticket = ticketService.getTicket();
                    System.out.println("---------------------------------");
                    System.out.println("Your ticket id " + ticket.getUuid());
                    System.out.println("Have a nice day");
                    System.out.println("---------------------------------");
                    break;
                }
                System.out.println("Have a nice day");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
