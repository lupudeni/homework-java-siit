package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.DBUtils;
import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Order;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class OrderDAOImplTest {

    private  OrderDAO sut;

    @Before
    public void setUp() throws SQLException {
        sut = new OrderDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
        DBUtils.setUp();
    }

    @After
    public void cleanUp() throws SQLException {
        DBUtils.cleanUp();
    }

    private Order getCustomOrder(int customerNumber) {
        return Order.builder()
                .orderDate(LocalDate.of(1995, 2, 4))
                .requiredDate(LocalDate.of(1995, 2, 5))
                .shippedDate(LocalDate.of(1995, 2, 6))
                .status("Shipped")
                .comments("test order")
                .customer(getCustomCustomer(customerNumber))
                .build();
    }

    private Customer getCustomCustomer(int customerNumber) {
        return Customer.builder()
                .customerNumber(customerNumber)
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

    private Order getExistingOrder() {
        return Order.builder()
                .orderNumber(10100)
                .orderDate(LocalDate.of(2003, 1, 10))
                .requiredDate(LocalDate.of(2003, 1, 18))
                .shippedDate(LocalDate.of(2003, 1, 14))
                .status("Shipped")
                .customer(Customer.builder().customerNumber(103).build())
                .orderDetailList(new ArrayList<>())
                .build();
    }

    @Test
    public void given_order_when_save_then_return_saved_order_with_new_unique_number() {
        //Given
        Order order = getCustomOrder(103);

        //when
        Order returnedOrder = sut.save(order);

        //then
        assertThat(returnedOrder.getOrderNumber()).isEqualTo(10101);
    }

    @Test
    public void given_first_order_when_save_then_return_order_with_first_unique_number() throws SQLException {
        //given
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0; ").executeUpdate();
        connection.prepareStatement("TRUNCATE table orders; ").executeUpdate();
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        Order order = getCustomOrder(103);

        //when
        Order returnedOrder = sut.save(order);

        //then
        assertThat(returnedOrder.getOrderNumber()).isEqualTo(10100);

    }

    @Test
    public void given_existing_number_when_find_by_number_then_return_order() {
        //given
        Order order = getExistingOrder();
        int orderNumber = 10100;

        //when
        Order returnedOrder = sut.findByNumber(orderNumber);

        //then
        assertThat(returnedOrder).isEqualTo(order);
    }

    @Test
    public void given_inexistent_order_number_when_find_by_number_then_return_null() {
        //given
        int orderNumber = 1;

        //when
        Order order = sut.findByNumber(orderNumber);

        //then
        assertThat(order).isNull();
    }


    @Test
    public void given_customer_number_when_find_by_customer_then_return_list_of_order() {
        //given
        Order order = getExistingOrder();

        int customerNumber = 103;

        //when
        List<Order> orders = sut.findByCustomerNumber(customerNumber);

        //then
        assertThat(orders).containsExactly(order);
    }

    @Test(expected = RuntimeException.class)
    public void given_inexistent_foreign_key_when_save_then_throw_exception() {
        //given
        Order order = getCustomOrder(1);

        //when
        sut.save(order);
    }

    @Test
    public void given_existing_order_with_new_data_when_update_then_return_true() {
        //given
        Order order = getExistingOrder();
        order.setStatus("Returned");

        //when
        boolean isUpdated = sut.update(order);

        //then
        assertThat(isUpdated).isTrue();
    }

    @Test
    public void given_inexistent_order_with_new_data_when_update_then_return_false() {
        //given
        Order order = getCustomOrder(104);
        order.setOrderNumber(1);

        //when
        boolean isUpdated = sut.update(order);

        //then
        assertThat(isUpdated).isFalse();
    }

    @Test
    public void given_existing_order_when_delete_then_return_true() throws SQLException {
        //given
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        String orders = "INSERT INTO orders(orderNumber, orderDate, requiredDate, shippedDate, status,  customerNumber) " +
                "VALUES (10101,'2003-01-10','2003-01-18','2003-01-14','Shipped',103);";
        connection.prepareStatement(orders).executeUpdate();

        int orderNumber = 10101;

        //when
        boolean isDeleted = sut.delete(orderNumber);

        //then
        assertThat(isDeleted).isTrue();
    }

}
