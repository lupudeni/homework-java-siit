package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Employee;
import com.homework.week15.jdbc.domain.Order;
import com.homework.week15.jdbc.domain.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class CustomerDAOImpl implements CustomerDAO {
//    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//    private OrderDAO orderDAO = new OrderDAOImpl();

    @Override
    public void update(Customer customer) {
        String query = "UPDATE customers " +
                "customerNumber = ?, " +
                "customerName = ?, " +
                "contactLastName = ?, " +
                "contactFirstName = ?, " +
                "phone = ?, " +
                "addressLine1 = ?, " +
                "addressLine2 = ? " +
                "city = ?, " +
                "state = ?, " +
                "postalCode = ?, " +
                "salesRepEmployeeNumber = ?, " +
                "creditLimit = ? " +
                "WHERE customerNumber = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        int rowsAffected = 0;
        try {
            preparedStatement.setInt(13, customer.getCustomerNumber());
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating customer number " + customer.getCustomerNumber());
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }

    }

    @Override
    public Customer findCustomerByNumber(int customerNumber) {
        String query = "SELECT * FROM customers c" +
                "JOIN payments p ON c.customerNumber = p.customerNumber " +
                "WHERE c.customerNumber = ?";
        PreparedStatement preparedStatement = getPreparedStatement(query);

        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractCustomerFromResultSet(resultSet);
            } else {
                return  null;
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving customer number " + customerNumber);
            throw new RuntimeException(e);
        }

    }

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        int salesRepEmployee = resultSet.getInt("salesRepEmployee");
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
//                .salesRepEmployee(salesRepEmployee == 0 ? null : employeeDAO.findByNumber(salesRepEmployee))
                .creditLimit(resultSet.getBigDecimal("creditLimit"))
                .build();

//        List<Order> orders = new ArrayList<>();
//        orders = orderDAO.findByCustomerNumber(resultSet.getInt("customerNumber"));
//        customer.setOrderList(orders);

        List<Payment> payments = new ArrayList<>();
        while (resultSet.next()) {
            Payment payment = extractPayment(resultSet, customer);
            payments.add(payment);
        }
        customer.setPaymentList(payments);

        return customer;
    }

    Payment extractPayment(ResultSet resultSet, Customer customer) throws SQLException {
        return Payment.builder()
                .customer(customer)
                .checkNumber(resultSet.getString("checkNumber"))
                .paymentDate(resultSet.getDate("paymentDate").toLocalDate())
                .amount(resultSet.getBigDecimal("amount"))
                .build();
    }

    private PreparedStatement getPreparedStatement(String query) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL, "siit", "siit");
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error while getting connection");
            throw new RuntimeException("Error while getting connection: ", e);
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
