package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Customer;

public interface CustomerDAO {
    Customer findCustomerByNumber(int customerNumber);
}
