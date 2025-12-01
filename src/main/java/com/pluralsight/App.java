package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/northwind";
        String username = "root";
        String password = "yearup";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();
        String query = "SELECT productName FROM products ";


        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String productName = results.getString("productName");
            System.out.println(productName);
        }

        connection.close();
    }
}



