package com.delphi.nice.training.service;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntroduceService {

    private static final Logger log = LogManager.getLogger(IntroduceService.class.getName());
    private final TicketService ticketService;
    private final ExitService exitService;

    public IntroduceService(TicketService ticketService, ExitService exitService) {
        this.ticketService = ticketService;
        this.exitService = exitService;
    }

    public void welcomeMessage() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Hello, do you want to park or leave?(P/L)");
                String line = br.readLine();
                if (line.equalsIgnoreCase("P")) {
                    if (ticketService.generateTicket()) {
                        System.out.println("---------------------------------");
                        System.out.println("Your ticket id: " + ticketService.getTicketDto().getUuid());
                        System.out.println("Your parking slot: " + ticketService.getParkingSlot());
                        System.out.println("Have a nice day");
                        System.out.println("---------------------------------");
                    }
                } else if (line.equalsIgnoreCase("L")) {
                    closeMessage(br);
                } else {
                    System.out.println("Have a nice day");
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    private void closeMessage(BufferedReader br) {
        try {
            System.out.println("Input you ID please");
            String line = br.readLine();
            long id = Long.parseLong(line);
            exitService.exit(id);
        } catch (NullPointerException e) {
            System.out.println("Incorrect id, try again");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
