package com.delphi.nice.training;

import com.delphi.nice.training.ticket.Ticket;
import com.delphi.nice.training.ticket.TicketDao;
import com.delphi.nice.training.ticket.TicketDaoService;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=parking_db";
//        Statement stmt;
//        ResultSet result;
//        Connection conn;
//        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//        String databaseUserName = "sa";
//        String databasePassword = "sa";
//        try {
//            Class.forName(driver).newInstance();
//            conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
//            stmt = conn.createStatement();
//            result = stmt.executeQuery("select * from tickets");
//            while (result.next()) {
//                int id = result.getInt(1);
//                String uuid = result.getString(2);
//                System.out.println("id = " + id);
//                System.out.println("uuid = " + uuid);
//            }
//            conn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<Ticket> list = ticketRepository.findAll();
//        list.forEach(System.out::println);
//    }
}
