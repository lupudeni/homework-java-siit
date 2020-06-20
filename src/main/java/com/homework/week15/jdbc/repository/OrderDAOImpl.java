package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class OrderDAOImpl implements OrderDAO {

    private final ProductDAO productDAO = new ProductDAOImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();

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
        Order order = Order.builder()
                .orderNumber(resultSet.getInt("orderNumber"))
                .orderDate(resultSet.getDate("orderDate").toLocalDate())
                .requiredDate(resultSet.getDate("requiredDate").toLocalDate())
                .shippedDate(resultSet.getDate("shippedDate").toLocalDate())
                .status(resultSet.getString("status"))
                .comments(resultSet.getString("comments"))
                .customer(customerDAO.findCustomerByNumber(resultSet.getInt("customerNumber")))
                .build();

        List<OrderDetail> orderDetails = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetail orderDetail = extractOrderDetailFromResultSet(resultSet, order);
            orderDetails.add(orderDetail);
        }
        order.setOrderDetailList(orderDetails);
        return order;
    }

    //for date conversion : toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
    private OrderDetail extractOrderDetailFromResultSet(ResultSet resultSet, Order order) throws SQLException {

        Product product = productDAO.findByCode(resultSet.getString("productCode"));
        return OrderDetail.builder()
                .order(order)
                .product(product)
                .quantityOrdered(resultSet.getInt("quantityOrdered"))
                .priceEach(resultSet.getBigDecimal("priceEach"))
                .orderLineNumber(resultSet.getInt("orderLineNumber"))
                .build();
    }

    @Override
    public List<Order> findByCustomerNumber(int customerNumber) {

        String query = "SELECT * FROM orders o " +
                "JOIN customers c ON o.customerNumber = c.customerNumber " +
                "WHERE o.customerNumber = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        List<Order> orders = new ArrayList<>();

        try {
            preparedStatement.setInt(1, customerNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = findByNumber(resultSet.getInt("orderNumber"));
                orders.add(order);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving order by customer number " + customerNumber);
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
        return orders;
    }

    @Override
    public void update(Order order) {
        String query = "UPDATE orders " +
                "orderNumber = ?, " +
                "orderDate = ?, " +
                "requiredDate = ?, " +
                "shippedDate = ?, " +
                "status = ?, " +
                "comments = ?, " +
                "customerNumber = ? " +
                "WHERE orderNumber = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        int rowsAffected = 0;
        try {
            preparedStatement.setInt(8, order.getOrderNumber());
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update failed");
            }
        } catch (SQLException e) {
            System.out.println("Error while updating order number " + order.getOrderNumber());
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    @Override
    public void delete(int orderNumber) {
        String query = "DELETE FROM orders WHERE orderNumber = ?";
        PreparedStatement preparedStatement = getPreparedStatement(query);
        int rowsAffected = 0;

        try {
            preparedStatement.setInt(1, orderNumber);
            rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order removed successfully");
            } else {
                System.out.println("Fail: no order number " + orderNumber + " found in database");
            }
        } catch (SQLException e) {
            System.out.println("Error while removing order number " + orderNumber);
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }

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
