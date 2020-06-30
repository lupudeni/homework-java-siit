package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.DBUtils;
import com.homework.week15.jdbc.domain.Employee;
import com.homework.week15.jdbc.domain.Office;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class EmployeeDAOImplTest {
    private EmployeeDAO sut;

    @Before
    public void setUp() throws SQLException {
        sut = new EmployeeDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
        DBUtils.setUp();
    }

    @After
    public void cleanUp() throws SQLException {
        DBUtils.cleanUp();
    }

    @Test
    public void given_employee_number_when_find_by_number_then_return_employee() {
        //given
        Employee employee = getEmployeeForTest();
        int employeeNumber = 1002;

        //when
        Employee returnedEmployee = sut.findByNumber(employeeNumber);

        //then
        assertThat(returnedEmployee).isEqualTo(employee);
    }

    @Test
    public void given_unknown_number_when_find_by_number_then_return_null() {
        //given
        int employeeNumber = 1;

        //when
        Employee returnedEmployee = sut.findByNumber(employeeNumber);

        //then
        assertThat(returnedEmployee).isNull();
    }

    private Employee getEmployeeForTest() {
        return Employee.builder()
                .employeeNumber(1002)
                .lastName("Murphy")
                .firstName("Diane")
                .extension("x5800")
                .email("dmurphy@classicmodelcars.com")
                .office(getOfficeForTest())
                .jobTitle("President")
                .build();
    }

    private Office getOfficeForTest() {
        return Office.builder()
                .officeCode("1")
                .city("San Francisco")
                .phone("+1 650 219 4782")
                .addressLine1("100 Market Street")
                .addressLine2("Suite 300")
                .state("CA")
                .country("USA")
                .postalCode("94080")
                .territory("NA")
                .build();
    }

    //        String offices = "INSERT  INTO `offices`(`officeCode`,`city`,`phone`,
    //        `addressLine1`,`addressLine2`,`state`,`country`,`postalCode`,`territory`) VALUES " +
    //                "('1','San Francisco','+1 650 219 4782',
    //                '100 Market Street','Suite 300','CA','USA','94080','NA');";
}
