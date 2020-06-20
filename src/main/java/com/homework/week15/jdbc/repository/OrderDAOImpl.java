package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;




public class OrderDAOImpl implements OrderDAO {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/classicmodels?serverTimezone=EET";

    @Override
    public void save(Order order) {
        String query = "INSERT INTO orders(" +
                "orderNumber, " +
                "orderDate, " +
                "requiredDate, " +
                "shippedDate, " +
                "status, " +
                "comments, " +
                "customerNumber) " +
                "VALUES(?,?,?,?,?,?,?)";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        int rowsAffected = 0;
        int paramIndex = 1;
        try {
            preparedStatement.setInt(paramIndex++, getNextIndex());
            preparedStatement.setDate(paramIndex++, Date.valueOf(order.getOrderDate()));
            preparedStatement.setDate(paramIndex++, Date.valueOf(order.getRequiredDate()));
            preparedStatement.setDate(paramIndex++, Date.valueOf(order.getShippedDate()));
            preparedStatement.setString(paramIndex++, order.getStatus());
            preparedStatement.setString(paramIndex++, order.getComments());
            preparedStatement.setInt(paramIndex, order.getCustomer().getCustomerNumber());

            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error while inserting order");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    private int getNextIndex() throws SQLException {
        String query = "SELECT MAX(orderNumber) FROM orders";
        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int index = resultSet.getInt("orderNumber");
            return ++index;
        } finally {
            closeConnection(preparedStatement);
        }
    }

    @Override
    public Order findByNumber(int orderNumber) {
        String query = "SELECT * FROM orders o " +
                "JOIN orderdetails od ON o.orderNumber = od.orderNumber " +
                "WHERE o.orderNumber = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);

        try {
            preparedStatement.setInt(1, orderNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractOrderFromResultSet(resultSet);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving order by number");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }


    }

    private Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return Order.builder()
                .orderNumber(resultSet.getInt("orderNumber"))
                .orderDate(resultSet.getDate("orderDate").toLocalDate())
                .requiredDate(resultSet.getDate("requiredDate").toLocalDate())
                .shippedDate(resultSet.getDate("shippedDate").toLocalDate())
                .status(resultSet.getString("status"))
                .comments(resultSet.getString("comments"))
                .customer(extractCustomerFromResultSet(resultSet))
                .orderDetail(extractOrderDetailFromResultSet(resultSet))
                .build();
    }
//for date conversion : toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    private OrderDetail extractOrderDetailFromResultSet(ResultSet resultSet) {
        return

    }

    private Customer extractCustomerFromResultSet(ResultSet resultSet) throws SQLException{
        Customer.builder()
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
                .salesRepEmployee(resultSet.getInt("salesRepEmployee") == 0 ? null : extractEmployeeFromResultSet(resultSet))
                .creditLimit(resultSet.getBigDecimal("creditLimit"))
                .build();
    }

    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        return Employee.builder()
                .employeeNumber(resultSet.getInt("employeeNumber"))
                .lastName(resultSet.getString("lastName"))
                .firstName(resultSet.getString("firstName"))
                .extension(resultSet.getString("extension"))
                .email(resultSet.getString("email"))
                .office(extractOfficeFromResultSet(resultSet))
                .reportsTo(resultSet.getInt("reportsTo") == 0 ? null : extractEmployeeFromResultSet(resultSet))
                .jobTitle(resultSet.getString("jobTitle"))
                .build();
    }

    private Office extractOfficeFromResultSet(ResultSet resultSet) throws SQLException{
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

    @Override
    public List<Order> findByCustomer(Customer customer) {
        return null;
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void delete(int orderNumber) {

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
