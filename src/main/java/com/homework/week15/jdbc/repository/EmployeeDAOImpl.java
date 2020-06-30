package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Employee;
import com.homework.week15.jdbc.domain.Office;

import java.sql.*;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final String connectionURL;

    public EmployeeDAOImpl(String connectionURL) {
        this.connectionURL = connectionURL;
    }

    public EmployeeDAOImpl() {
        this(CONNECTION_URL);
    }

    @Override
    public Employee findByNumber(int employeeNumber) {
        String query = "SELECT * FROM employees e " +
                "JOIN offices o ON e.officeCode = o.officeCode " +
                "WHERE e.employeeNumber = ?";
        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            preparedStatement.setInt(1, employeeNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractEmployeeFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving Employee");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        int superior = resultSet.getInt("reportsTo");
        return Employee.builder()
                .employeeNumber(resultSet.getInt("employeeNumber"))
                .lastName(resultSet.getString("lastName"))
                .firstName(resultSet.getString("firstName"))
                .extension(resultSet.getString("extension"))
                .email(resultSet.getString("email"))
                .office(extractOfficeFromResultSet(resultSet))
                .reportsTo(superior == 0 ? null : findByNumber(superior))
                .jobTitle(resultSet.getString("jobTitle"))
                .build();

    }

    private Office extractOfficeFromResultSet(ResultSet resultSet) throws SQLException {
        return Office.builder()
                .officeCode(resultSet.getString("officeCode"))
                .city(resultSet.getString("city"))
                .phone(resultSet.getString("phone"))
                .addressLine1(resultSet.getString("addressLine1"))
                .addressLine2(resultSet.getString("addressLine2"))
                .state(resultSet.getString("state"))
                .country(resultSet.getString("country"))
                .postalCode(resultSet.getString("postalCode"))
                .territory(resultSet.getString("territory"))
                .build();
    }

    private PreparedStatement getPreparedStatement(String query) {
        try {
            Connection connection = DriverManager.getConnection(connectionURL, "siit", "siit");
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error while getting connection");
            throw new RuntimeException(e);
        }
    }

    private void closeConnection(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error while closing connection");
        }
    }

}
