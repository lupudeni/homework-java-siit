package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Employee;

public interface EmployeeDAO {

    void update(Employee employee);

    Employee findByNumber(int employeeNumber);
}
