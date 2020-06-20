package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Order;

import java.util.List;

public interface OrderDAO {

    void save(Order order);

    Order findByNumber(int orderNumber);

    List<Order> findByCustomerNumber(int customerNumber);

    void update(Order order);

    void delete(int orderNumber);

}
