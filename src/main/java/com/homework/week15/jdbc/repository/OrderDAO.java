package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Order;

import java.util.List;

public interface OrderDAO {

    void save(Order order);

    //find by -> number, status, date, customer number
    Order findByNumber(int orderNumber);

    List<Order> findByCustomer(Customer customer);

    void update(Order order);

    void delete(int orderNumber);

}
