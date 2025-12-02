package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Northwind";
        String username = args[0];
        String password = args[1];

        Connection connection = DriverManager.getConnection(url, username, password);

        String query = """
                SELECT productID, productName, unitPrice, UnitsInStock
                FROM Products
                WHERE productName like ? or supplierID like ?;
                """;

        PreparedStatement statement = connection.prepareStatement(query);

        String productName = "%TOFU%";
        int supplierID = 2;

        statement.setString(1, productName);
        statement.setInt(2, supplierID);


        ResultSet results = statement.executeQuery();

        while (results.next()) {
            int productID = results.getInt("productID");  ///   String title = results.getString("title"); and String description = results.getString(2); are the same
            productName = results.getString(2);
             int unitPrice = results.getInt(3);
             int UnitsInStock = results.getInt(4);

             System.out.println(productID);
            System.out.println(productName);
            System.out.println(unitPrice);
            System.out.println(UnitsInStock);
        }

        results.close();
        statement.close();
        connection.close();
    }
}



