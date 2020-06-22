package com.homework.week15.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class DBTestUtils {

    public static void createTestDBEnvironment() throws IOException, SQLException {
        String query = Files.readString(
                Path.of("src", "test", "java", "com", "homework", "week15", "jdbc", "test_db_structure.sql"));
        PreparedStatement preparedStatement = getPreparedStatement(query);
        preparedStatement.executeQuery();
        closeConnection(preparedStatement);
    }

    private static PreparedStatement getPreparedStatement(String query) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=EET", "siit", "siit");
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error while getting connection");
            throw new RuntimeException("Error while getting connection: ", e);
        }
    }

    private static void closeConnection(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error while closing connection");
        }
    }

}
