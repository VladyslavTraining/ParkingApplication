package com.delphi.nice.training.service;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntroduceServiceImpl implements IntroduceService {

    private final TicketServiceImpl ticketServiceImpl;
    private final ExitServiceImpl exitServiceImpl;

    public IntroduceServiceImpl(TicketServiceImpl ticketServiceImpl, ExitServiceImpl exitServiceImpl) {
        this.ticketServiceImpl = ticketServiceImpl;
        this.exitServiceImpl = exitServiceImpl;
    }

    public void welcomeMessage() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Hello, do you want to park or leave?(P/L)");
                String line = br.readLine();
                if (line.equalsIgnoreCase("P")) {
                    if (ticketServiceImpl.generateTicket()) {
                        System.out.println("---------------------------------");
                        System.out.println("Your ticket id: " + ticketServiceImpl.getTicketDto().getUuid());
                        System.out.println("Your parking slot: " + ticketServiceImpl.getParkingSlot());
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
            System.out.println(id);
            exitServiceImpl.exit(id);
        } catch (NullPointerException e) {
            System.out.println("Incorrect id, try again");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
