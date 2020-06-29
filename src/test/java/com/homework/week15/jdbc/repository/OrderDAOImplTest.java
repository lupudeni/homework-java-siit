package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Order;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderDAOImplTest {

    private static OrderDAO sut;

    @BeforeClass
    public static void setUp() {
        sut = new OrderDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
    }

    @AfterClass
    public static void cleanUp() throws SQLException {
        //delete from orders where orderNumber in ( 10101, 10102);

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        connection.prepareStatement("DELETE FROM orders WHERE orderNumber = 10101").executeUpdate();
    }

    private Order createOrderForSaveTest() {
        return Order.builder()
                .orderDate(LocalDate.of(1995, 2, 4))
                .requiredDate(LocalDate.of(1995, 2, 5))
                .shippedDate(LocalDate.of(1995, 2, 6))
                .status("Shipped")
                .comments("test order")
                .customer(createCustomerForSaveTest())
                .build();
    }

    private Customer createCustomerForSaveTest() {
      return Customer.builder()
                .customerNumber(103)
                .customerName("Atelier graphique")
                .contactLastName("Schmitt")
                .contactFirstName("Carine")
                .phone("40.32.2555")
                .addressLine1("54, rue Royale")
                .city("Nantes")
                .postalCode("44000")
                .country("France")
                .creditLimit(new BigDecimal("21000"))
                .build();
    }


    @Test
    public void given_order_when_save_then_return_saved_order_with_new_unique_number() {
        //Given
        Order order = createOrderForSaveTest();

        //when
        Order returnedOrder = sut.save(order);

        //then
        assertThat(returnedOrder.getOrderNumber()).isEqualTo(10101);
    }
    // //preparedStatement.executeUpdate()


    @Test
    public void given_existing_number_when_find_by_number_then_return_order() {
        //given
        Order order = Order.builder()
                .orderNumber(10100)
                .orderDate(LocalDate.of(2003, 1, 6))
                .requiredDate(LocalDate.of(2003, 1, 13))
                .shippedDate(LocalDate.of(2003, 1, 10))
                .status("Shipped")
                .customer(Customer.builder().customerNumber(103).build())
                .orderDetailList(new ArrayList<>())
                .build();
        int orderNumber = 10100;

        //when
        Order returnedOrder = sut.findByNumber(orderNumber);

        //then
        assertThat(returnedOrder).isEqualTo(order);
    }
    //preparedStatement.executeUpdate()

    @Test
    public void given_customer_number_when_find_by_customer_then_return_list_of_order() {
        //given
        Order order = Order.builder()
                .orderNumber(10100)
                .orderDate(LocalDate.of(2003, 1, 6))
                .requiredDate(LocalDate.of(2003, 1, 13))
                .shippedDate(LocalDate.of(2003, 1, 10))
                .status("Shipped")
                .customer(Customer.builder().customerNumber(103).build())
                .orderDetailList(new ArrayList<>())
                .build();

        int customerNumber = 103;

        //when
        List<Order> orders = sut.findByCustomerNumber(customerNumber);

        //then
        assertThat(orders).containsExactly(order);
    }

    @Test
    public void given() throws SQLException {
        //given
    }
}
