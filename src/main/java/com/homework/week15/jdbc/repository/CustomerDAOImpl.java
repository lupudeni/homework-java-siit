package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Employee;
import com.homework.week15.jdbc.domain.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class CustomerDAOImpl implements CustomerDAO {

    private final String connectionURL;

    public CustomerDAOImpl(String connectionURL) { //for testing
        this.connectionURL = connectionURL;
    }
    public CustomerDAOImpl() {
        this(CONNECTION_URL);
    }

    @Override
    public Customer findCustomerByNumber(int customerNumber) {
        String query = "SELECT * FROM customers c " +
                "JOIN payments p ON c.customerNumber = p.customerNumber " +
                "WHERE c.customerNumber = ?";
        PreparedStatement preparedStatement = getPreparedStatement(query);

        try {
            preparedStatement.setInt(1, customerNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractCustomerFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving customer number " + customerNumber);
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }

    }

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int salesRepEmployee = resultSet.getInt("salesRepEmployeeNumber");
        Customer customer = Customer.builder()
                .customerNumber(resultSet.getInt("customerNumber"))
                .customerName(resultSet.getString("customerName"))
                .contactLastName(resultSet.getString("contactLastName"))
                .contactFirstName(resultSet.getString("contactFirstName"))
                .phone(resultSet.getString("phone"))
                .addressLine1(resultSet.getString("addressLine1"))
                .addressLine2(resultSet.getString("addressLine2"))
                .city(resultSet.getString("city"))
                .postalCode(resultSet.getString("postalCode"))
                .country(resultSet.getString("country"))
                .salesRepEmployee(salesRepEmployee == 0 ? null : Employee.builder()
                        .employeeNumber(salesRepEmployee)
                        .build())
                .creditLimit(resultSet.getBigDecimal("creditLimit"))
                .build();

        List<Payment> payments = new ArrayList<>();
        do  {
            Payment payment = extractPayment(resultSet, customer.getCustomerNumber());
            payments.add(payment);
        } while (resultSet.next());
        customer.setPaymentList(payments);

        return customer;
    }

    Payment extractPayment(ResultSet resultSet, int customerNumber) throws SQLException {
        return Payment.builder()
                .customerNumber(customerNumber)
                .checkNumber(resultSet.getString("checkNumber"))
                .paymentDate(resultSet.getDate("paymentDate").toLocalDate())
                .amount(resultSet.getBigDecimal("amount"))
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
