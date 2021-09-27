package com.delphi.nice.training.connection;

import com.delphi.nice.training.auth.User;
import com.delphi.nice.training.security.UserRole;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Service
public class Database {

    public Optional<User> getUserByUserName(String username) {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=parking_db";
        Statement stmt;
        ResultSet result;
        Connection conn;
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String databaseUserName = "sa";
        String databasePassword = "sa";
        User user;
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url, databaseUserName, databasePassword);
            stmt = conn.createStatement();
            result = stmt.executeQuery(String.format("select * from users where username='%s'", username));
            while (result.next()) {
                UserRole userRole = result.getString(4).equals("ROLE_ADMIN") ? UserRole.ADMIN : UserRole.USER;
                user = new User(
                        result.getLong(1),
                        result.getString(2),
                        result.getString(3),
                        userRole,
                        result.getBoolean(5),
                        result.getBoolean(6),
                        result.getBoolean(7),
                        result.getBoolean(8)
                );
                conn.close();
                return Optional.of(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

}
