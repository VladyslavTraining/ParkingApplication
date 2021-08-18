package com.delphi.nice.training.service;

import com.delphi.nice.training.model.cards.ParkingCardDto;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

public class Main {
    public static void main(String[] args) {
//        ApplicationContext app = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        ClientDto clientDto = app.getBean(ClientDto.class);
//        clientDto.setCardNumber(123L);
//        clientDto.setLicencePlate("AB123AB");
//        System.out.println(clientDto);

//        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
//        ParkingCardDto clientDto = app.getBean("client", ParkingCardDto.class);
//        clientDto.setLicencePlate("test123");
//        clientDto.setCardNumber(123L);

//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            System.out.println("Hello, do you want to stay at this parking?(Y/N)");
//            while (true) {
//                String line = br.readLine();
//                if (!line.equalsIgnoreCase("Y")) {
//                    break;
//                }
//                System.out.println("Please, insert you LicencePlate and CardNumber");
//                System.out.print("LicencePlate : ");
//                clientDto.setLicencePlate(br.readLine());
//                System.out.print("CardNumber : ");
//                clientDto.setParkingCardNumber(Long.parseLong(br.readLine()));
//                break;
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong");
//        }
        try (FileWriter outputStreamWriter = new FileWriter("parkingArea.json", true)) {
            outputStreamWriter.write('[');
            for (int i = 1; i <= 30; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("parkingSpot", i);
                jsonObject.put("isParked", false);

                outputStreamWriter.write(jsonObject + "," + System.lineSeparator());

            }
            outputStreamWriter.write(']');
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

