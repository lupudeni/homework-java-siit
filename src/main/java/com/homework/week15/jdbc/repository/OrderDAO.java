package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Order;

import java.sql.ResultSet;
import java.util.List;

public interface OrderDAO {

    Order save(Order order);

    Order findByNumber(int orderNumber);

    List<Order> findByCustomerNumber(int customerNumber);

    boolean update(Order order);

    boolean delete(int orderNumber);

}
