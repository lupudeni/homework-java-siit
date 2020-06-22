package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Customer;

public interface CustomerDAO {
    void update(Customer customer);

    Customer findCustomerByNumber(int customerNumber);
}
