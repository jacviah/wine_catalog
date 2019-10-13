package by.jacviah.winery;

import java.sql.Connection;
import java.sql.DriverManager;

public class Run {
    public static void main(String args[]) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wine_catalog?serverTimezone=EST5EDT", "root", "root");
            connection.close();
        } catch (
                Exception e) {
            e.printStackTrace();

        }
    }
}