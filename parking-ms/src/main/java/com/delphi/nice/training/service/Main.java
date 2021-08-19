package com.delphi.nice.training.service;

import com.delphi.nice.training.model.ParkingSlotDto;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
//        ApplicationContext app = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        ClientDto clientDto = app.getBean(ClientDto.class);
//        clientDto.setCardNumber(123L);
//        clientDto.setLicencePlate("AB123AB");
//        System.out.println(clientDto);

//        fillJSONFile();
        checkingSpot(10);

    }


    private static void checkingSpot(int parkingSpot) {
        JSONParser jsonParser = new JSONParser();
        ParkingSlotDto dto;
        try (FileReader reader = new FileReader("parkingArea.json")) {
            Object obj = jsonParser.parse(reader);
            JSONArray employeeList = (JSONArray) obj;
            JSONObject itemArr = (JSONObject) employeeList.get(parkingSpot - 1);
            dto = parseToObj(itemArr);
            if (!dto.isParked()) {
                itemArr.replace("isParked", true);
                System.out.println("ParkingSpot # " + parkingSpot + " is busy now");
                FileWriter fw = new FileWriter("parkingArea.json");
                employeeList.writeJSONString(fw);
                fw.close();
            } else {
                System.out.println("Spot # " + parkingSpot + " is busy");
                System.out.println("Free spots are :");
                for (int i = 0; i < 30; i++) {
                    ParkingSlotDto freeSpot = parseToObj((JSONObject) employeeList.get(i));
                    if (!freeSpot.isParked()) {
                        System.out.println(freeSpot.getParkingSpot());
                    }
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


//    private static void introduce() {
//        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
//            System.out.println("Hello, do you want to stay at this parking?(Y/N)");
//            while (true) {
//                String line = br.readLine();
//                if (!line.equalsIgnoreCase("Y")) {
//                    break;
//                }
//                System.out.println("Please, insert you LicencePlate and CardNumber");
//                System.out.print("LicencePlate : ");
//                System.out.print("CardNumber : ");
//                break;
//            }
//        } catch (IOException e) {
//            System.out.println("Something went wrong");
//        }
//    }

    private static ParkingSlotDto parseToObj(JSONObject employee) {
        Gson gson = new Gson();
        return gson.fromJson(employee.toJSONString(), ParkingSlotDto.class);
    }

    public static void fillJSONFile() {
        try (FileWriter fw = new FileWriter("parkingArea1.json")) {
            fw.write("[");
            for (int i = 1; i <= 30; i++) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("parkingSpot", i);
                jsonObject.put("isParked", false);
                fw.write(jsonObject.toJSONString() + ",");
            }
            fw.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

