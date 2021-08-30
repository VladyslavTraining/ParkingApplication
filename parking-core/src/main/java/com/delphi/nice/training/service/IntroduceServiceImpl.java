package com.delphi.nice.training.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
@Component
public class IntroduceServiceImpl implements IntroduceService {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ExitService exitService;

    public void welcomeMessage() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Hello, do you want to park or leave?(P/L)");
                String line = br.readLine();
                if (line.equalsIgnoreCase("P")) {
                    if (ticketService.generateTicket()) {
                        System.out.println("---------------------------------");
                        System.out.println("Your ticket id: " + ticketService.getTicketID());
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
}
