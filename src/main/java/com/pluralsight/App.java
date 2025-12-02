package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Scanner scanner =  new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/Northwind";
        String username = args[0];
        String password = args[1];

        Connection connection = DriverManager.getConnection(url, username, password);

        String query = """
                SELECT productID, productName, unitPrice, unitsInStock
                FROM Products
                WHERE productName like ? or supplierID = ?;
                """;

        PreparedStatement statement = connection.prepareStatement(query);

        System.out.print("What is the supplierID:");
        int supplierID = scanner.nextInt();
        scanner.nextLine();
        System.out.print("What is the productName:");
        String productName = scanner.nextLine();



        //String productName = "%TOFU%";
       // int supplierID = 2;

        statement.setString(1, "%"+productName+"%");
        statement.setInt(2, supplierID);


        ResultSet results = statement.executeQuery();

        while (results.next()) {
            int productID = results.getInt("productID");  ///   String title = results.getString("title"); and String description = results.getString(2); are the same
            productName = results.getString(2);
            int unitPrice = results.getInt(3);
            int unitsInStock = results.getInt(4);

            System.out.println("---------------------------");
            System.out.println("ID: " + productID);
            System.out.println("supplierID " + supplierID);
            System.out.println("Name: " + productName);
            System.out.println("Price: $" + unitPrice);
            System.out.println("Stock: " + unitsInStock);
            System.out.println("---------------------------");
        }

        results.close();
        statement.close();
        connection.close();
    }
}



