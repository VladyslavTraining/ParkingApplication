package com.delphi.nice.training.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
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
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("parkingArea.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray employeeList = (JSONArray) obj;
            System.out.println(employeeList);
            employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
    private static void parseEmployeeObject(JSONObject employee)
    {
        System.out.println(employee.get("parkingSpot").toString());
        System.out.println(employee.get("isParked").toString());
    }
}

