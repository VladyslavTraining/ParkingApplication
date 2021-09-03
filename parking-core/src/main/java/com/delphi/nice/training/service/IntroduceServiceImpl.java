package com.delphi.nice.training.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(IntroduceServiceImpl.class);

    public void welcomeMessage() {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in))) {
            while (true) {
                logger.debug("Hello, do you want to park or leave?(P/L)");
                String line = br.readLine();
                if (line.equalsIgnoreCase("P")) {
                    if (ticketService.generateTicket()) {
                        logger.debug("---------------------------------");
                        logger.info("Your ticket id: " + ticketService.getTicketID());
                        logger.info("Your parking slot: " + ticketService.getParkingSlot());
                        logger.debug("Have a nice day");
                        logger.debug("---------------------------------");
                    }
                } else if (line.equalsIgnoreCase("L")) {
                    closeMessage(br);
                } else {
                    logger.info("Have a nice day");
                    break;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    private void closeMessage(BufferedReader br) {
        try {
            logger.info("Input you ID please");
            String line = br.readLine();
            long id = Long.parseLong(line);
            exitService.exit(id);
        } catch (NullPointerException e) {
            logger.info("Incorrect id, try again");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
