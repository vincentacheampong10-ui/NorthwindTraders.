package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/Northwind";
        String username = args[0];
        String password = args[1];

        System.out.print("What is the username:");
        String userInput = scanner.nextLine();

        System.out.print("What is the password:");
        String userPassword = scanner.nextLine();

        if (userInput.equalsIgnoreCase(username) && userPassword.equalsIgnoreCase(password)) {
            System.out.println("Login in successfully");
        } else {
            System.out.println("Login failed");
            return;
        }

        displayProduct(url, username, password, scanner);
    }

    private static void displayProduct(String url, String username, String password, Scanner scanner) throws SQLException {
        // Connection connection = DriverManager.getConnection(url, username, password);

        String query = """
                SELECT productID, productName, unitPrice, unitsInStock
                FROM Products
                WHERE productName like ? or supplierID = ?;
                """;

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            System.out.print("What is the supplierID:");
            int supplierID = scanner.nextInt();
            scanner.nextLine();
            System.out.print("What is the productName:");
            String productName = scanner.nextLine();

            //String productName = "%TOFU%";
            // int supplierID = 2;

            statement.setString(1, "%" + productName + "%");
            statement.setInt(2, supplierID);

            try (ResultSet results = statement.executeQuery()) {

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
            }
        }

                query = """
                        SELECT CustomerID, address, city, phone
                        FROM customers
                        WHERE CustomerID like ? or city like ?;
                        """;

                try (Connection connection = DriverManager.getConnection(url, username, password);
                     PreparedStatement statement = connection.prepareStatement(query)) {

                    System.out.print("What is the CustomerID:");
                    String CustomerID = scanner.nextLine();
                    scanner.nextLine();
                    System.out.print("What is the city:");
                    String city = scanner.nextLine();

                    //String productName = "%TOFU%";
                    // int supplierID = 2;

                    statement.setString(1, "%" + CustomerID + "%");
                    statement.setString(2,"%" + city + "%");

                    try (ResultSet results = statement.executeQuery()) {

                        while (results.next()) {
                            CustomerID = results.getString("CustomerID");  ///   String title = results.getString("title"); and String description = results.getString(2); are the same
                            int address = results.getInt(2);
                            city = results.getString(3);
                            int phone = results.getInt(4);

                            System.out.println("---------------------------");
                            System.out.println("ID: " + CustomerID);
                            System.out.println("address " + address);
                            System.out.println("city: " + city);
                            System.out.println("phone: " + phone);
                            System.out.println("---------------------------");
                        }
                    }
                }
            }
        }







