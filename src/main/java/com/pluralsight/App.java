package com.pluralsight;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Sakila";
        String username = args[0];
        String password = args[1];

        Connection connection = DriverManager.getConnection(url, username, password);

        String query = """
                SELECT title, description, release_year, length
                FROM film
                WHERE title like ?
                """;

        PreparedStatement statement = connection.prepareStatement(query);

        String searchTerm = "%AIR%";
        statement.setString(1, searchTerm);

        ResultSet results = statement.executeQuery();

        while (results.next()) {
            String title = results.getString("title");  ///   String title = results.getString("title"); and String description = results.getString(2); are the same
            String description = results.getString(2);
             int releaseYear = results.getInt(3);
             int length = results.getInt(4);

             System.out.println(title);
            System.out.println(description);
            System.out.println(releaseYear);
            System.out.println(length);
        }

        results.close();
        statement.close();
        connection.close();
    }
}



