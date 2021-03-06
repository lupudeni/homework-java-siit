package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class OrderDAOImpl implements OrderDAO {

    private final String connectionUrl;

    public OrderDAOImpl(String connection) { //used for testing
        this.connectionUrl = connection;
    } //for testing

    public OrderDAOImpl() {
        this(CONNECTION_URL);
    }

    @Override
    public Order save(Order order) {
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
        int paramIndex = 1;
        try {
            int orderNumber = getNewOrderNumber();
            preparedStatement.setInt(paramIndex++, orderNumber);
            preparedStatement.setDate(paramIndex++, Date.valueOf(order.getOrderDate()));
            preparedStatement.setDate(paramIndex++, Date.valueOf(order.getRequiredDate()));
            preparedStatement.setDate(paramIndex++, Date.valueOf(order.getShippedDate()));
            preparedStatement.setString(paramIndex++, order.getStatus());
            preparedStatement.setString(paramIndex++, order.getComments());
            preparedStatement.setInt(paramIndex, order.getCustomer().getCustomerNumber());

            preparedStatement.executeUpdate();
            order.setOrderNumber(orderNumber);
            return order;
        } catch (SQLException e) {
            System.out.println("Error while inserting order");
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    private int getNewOrderNumber() throws SQLException {
        String query = "SELECT MAX(orderNumber) AS maxOrderNumber FROM orders";
        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int index = resultSet.getInt("maxOrderNumber");
            if (index != 0) {
                return ++index;
            }
            return 10100;
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
                .customer(Customer.builder()
                        .customerNumber(resultSet.getInt("customerNumber"))
                        .build())
                .build();

        List<OrderDetail> orderDetails = populateOrderDetailList(resultSet, order);
        order.setOrderDetailList(orderDetails);
        return order;
    }

    private List<OrderDetail> populateOrderDetailList(ResultSet resultSet, Order order) throws SQLException {
        List<OrderDetail> orderDetails = new ArrayList<>();
        while (resultSet.next()) {
            OrderDetail orderDetail = extractOrderDetailFromResultSet(resultSet, order);
            orderDetails.add(orderDetail);
        }
        return orderDetails;

    }

    private OrderDetail extractOrderDetailFromResultSet(ResultSet resultSet, Order order) throws SQLException {

        return OrderDetail.builder()
                .order(order)
                .product(Product.builder()
                        .productCode(resultSet.getString("productCode"))
                        .build())
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
    public boolean update(Order order) {
        String query = "UPDATE orders SET " +
                "orderDate = ?, " +
                "requiredDate = ?, " +
                "shippedDate = ?, " +
                "status = ?, " +
                "comments = ?, " +
                "customerNumber = ? " +
                "WHERE orderNumber = ?";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        try {
            preparedStatement.setDate(1, Date.valueOf(order.getOrderDate()));
            preparedStatement.setDate(2, Date.valueOf(order.getRequiredDate()));
            preparedStatement.setDate(3, Date.valueOf(order.getShippedDate()));
            preparedStatement.setString(4, order.getStatus());
            preparedStatement.setString(5, order.getComments());
            preparedStatement.setInt(6, order.getCustomer().getCustomerNumber());
            preparedStatement.setInt(7, order.getOrderNumber());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error while updating order number " + order.getOrderNumber());
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    @Override
    public boolean delete(int orderNumber) {
        String query = "DELETE FROM orders WHERE orderNumber = ?";
        PreparedStatement preparedStatement = getPreparedStatement(query);
        int rowsAffected;

        try {
            preparedStatement.setInt(1, orderNumber);
            rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error while removing order number " + orderNumber);
            throw new RuntimeException(e);
        } finally {
            closeConnection(preparedStatement);
        }
    }

    private PreparedStatement getPreparedStatement(String query) {
        try {
            Connection connection = DriverManager.getConnection(connectionUrl, "siit", "siit");
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
