package com.delphi.nice.training.service;

import com.delphi.nice.training.service.ParkingService;
import com.delphi.nice.training.service.TicketService;

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
                    new TicketService();
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
