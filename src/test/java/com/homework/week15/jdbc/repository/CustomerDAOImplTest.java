package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.DBUtils;
import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Employee;
import com.homework.week15.jdbc.domain.Payment;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class CustomerDAOImplTest {
    private CustomerDAO sut;

    @Before
    public void setUp() throws SQLException {
        sut = new CustomerDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
        DBUtils.setUp();
    }

    @After
    public void cleanUp() throws SQLException {
        DBUtils.cleanUp();
    }

    @Test
    public void given_customer_number_when_find_by_number_then_return_customer() {
        //given
        Customer customer = getCustomerForTest();
        int customerNumber = 103;

        //when
        Customer returnedCustomer = sut.findCustomerByNumber(customerNumber);

        //then
        assertThat(returnedCustomer).isEqualTo(customer);
    }

    @Test
    public void given_unknown_customer_number_when_find_by_number_then_return_null() {
        //given
        int customerNumber = 104;

        //when
        Customer returnedCustomer = sut.findCustomerByNumber(customerNumber);

        //then
        assertThat(returnedCustomer).isNull();
    }

    private Customer getCustomerForTest() {
        return Customer.builder()
                .customerNumber(103)
                .customerName("Atelier graphique")
                .contactLastName("Schmitt")
                .contactFirstName("Carine ")
                .phone("40.32.2555")
                .addressLine1("54, rue Royale")
                .city("Nantes")
                .postalCode("44000")
                .country("France")
                .salesRepEmployee(Employee.builder()
                        .employeeNumber(1002)
                        .build())
                .creditLimit(new BigDecimal("21000.00"))
                .paymentList(List.of(Payment.builder()
                        .checkNumber("HQ336336")
                        .paymentDate(LocalDate.of(2004, 10, 19))
                        .amount(new BigDecimal("6066.78"))
                        .customerNumber(103)
                        .build())
                )
                .build();
    }


}
