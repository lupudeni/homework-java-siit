package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.DBTestUtils;
import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Order;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
public class OrderDAOImplTest {

    private OrderDAO sut;

    @Before
    public void setUp() {
        sut = new OrderDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
    }

    @BeforeClass
    public static void connect() throws IOException, SQLException {
        DBTestUtils.createTestDBEnvironment();
    }

    private Order createOrderForTest() {
        return Order.builder()
                .orderDate(LocalDate.of(1995, 2, 4))
                .requiredDate(LocalDate.of(1995, 2, 5))
                .shippedDate(LocalDate.of(1995, 2, 6))
                .status("Shipped")
                .comments("test order")
                .customer(createCustomerForTest())
                .build();
    }

    private Customer createCustomerForTest() {
      return Customer.builder()
                .customerNumber(1)
                .customerName("name")
                .contactLastName("contactLastName")
                .contactFirstName("contactFirstName")
                .phone("phone")
                .addressLine1("addressLine1")
                .addressLine2("addressLine2")
                .city("city")
                .postalCode("postalCode")
                .country("country")
                .creditLimit(new BigDecimal("100"))
                .build();
    }

    @Test
    public void given_order_when_save_then_return_saved_order_with_new_unique_number() {
        //Given
        Order order = createOrderForTest();

        //when
        Order returnedOrder = sut.save(order);

        //then
        assertThat(returnedOrder.getOrderNumber()).isEqualTo(1);
    }
}
