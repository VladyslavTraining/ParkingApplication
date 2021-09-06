package com.delphi.nice.training.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
@Service
public class IntroduceServiceImpl implements IntroduceService {

    private final TicketService ticketService;
    private final ExitService exitService;


    @Autowired
    public IntroduceServiceImpl(TicketService ticketService, ExitService exitService) {
        this.ticketService = ticketService;
        this.exitService = exitService;
    }

    public void welcomeMessage() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))) {
            while (true) {
                log.debug("Hello, do you want to park or leave?(P/L)");
                String line = br.readLine();
                if (line.equalsIgnoreCase("P")) {
                    if (ticketService.generateTicket()) {
//                        logger.info("---------------------------------");
                        log.info("Your ticket id: " + ticketService.getTicketID());
                        log.info("Your parking slot: " + ticketService.getParkingSlot());
                        log.debug("Have a nice day");
                        log.info("---------------------------------");
                    }
                } else if (line.equalsIgnoreCase("L")) {
                    closeMessage(br);
                } else {
                    log.debug("Have a nice day");
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

    }

    private void closeMessage(BufferedReader br) {
        try {
            log.debug("Input you ID please");
            String line = br.readLine();
            long id = Long.parseLong(line);
            log.info("ticket id " + id + " has left from parking area");
            log.info("---------------------------------");
            exitService.exit(id);
        } catch (NullPointerException e) {
            log.debug("Incorrect id, try again");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
