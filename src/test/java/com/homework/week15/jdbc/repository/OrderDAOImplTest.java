package com.homework.week15.jdbc.repository;

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

    private static OrderDAO sut;


    @Before
    public void setUp() throws SQLException {
        sut = new OrderDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        String offices = "INSERT  INTO `offices`(`officeCode`,`city`,`phone`,`addressLine1`,`addressLine2`,`state`,`country`,`postalCode`,`territory`) VALUES " +
                "('1','San Francisco','+1 650 219 4782','100 Market Street','Suite 300','CA','USA','94080','NA');";

        String employees = "INSERT  INTO `employees`(`employeeNumber`,`lastName`,`firstName`,`extension`,`email`,`officeCode`,`reportsTo`,`jobTitle`) VALUES " +
                "(1002,'Murphy','Diane','x5800','dmurphy@classicmodelcars.com','1',NULL,'President');";

        String customers = "INSERT INTO `customers`(`customerNumber`,`customerName`,`contactLastName`,`contactFirstName`,`phone`,`addressLine1`,`addressLine2`,`city`,`state`,`postalCode`,`country`,`salesRepEmployeeNumber`,`creditLimit`) " +
                "VALUES (103,'Atelier graphique','Schmitt','Carine ','40.32.2555','54, rue Royale',NULL,'Nantes',NULL,'44000','France',1002,'21000.00');";

        String orders = "INSERT INTO orders(orderNumber, orderDate, requiredDate, shippedDate, status,  customerNumber) " +
                "VALUES (10100,'2003-01-10','2003-01-18','2003-01-14','Shipped',103);";

        String productLines = "INSERT INTO `productlines`(`productLine`,`textDescription`,`htmlDescription`,`image`) VALUES " +
                "('Classic Cars','Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.',NULL,NULL);";

        String products = "INSERT INTO `products`(`productCode`,`productName`,`productLine`,`productScale`,`productVendor`,`productDescription`,`quantityInStock`,`buyPrice`,`MSRP`) VALUES " +
                "('S10_1949','1952 Alpine Renault 1300','Classic Cars','1:10','Classic Metal Creations','Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',7305,'98.58','214.30');\n";

        String orderDetails = "INSERT INTO `orderdetails`(`orderNumber`,`productCode`,`quantityOrdered`,`priceEach`,`orderLineNumber`) VALUES " +
                "(10100,'S10_1949',30,'214.30',3);";

        String payments = "INSERT INTO `payments`(`customerNumber`,`checkNumber`,`paymentDate`,`amount`) VALUES " +
                "(103,'HQ336336','2004-10-19','6066.78');";


        connection.prepareStatement(offices).executeUpdate();
        connection.prepareStatement(employees).executeUpdate();
        connection.prepareStatement(customers).executeUpdate();
        connection.prepareStatement(orders).executeUpdate();
        connection.prepareStatement(productLines).executeUpdate();
        connection.prepareStatement(products).executeUpdate();
        connection.prepareStatement(orderDetails).executeUpdate();
        connection.prepareStatement(payments).executeUpdate();
    }

    @After
    public void cleanUp() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0; ").executeUpdate();
        connection.prepareStatement("TRUNCATE TABLE payments;").executeUpdate();
        connection.prepareStatement("TRUNCATE TABLE orderdetails;").executeUpdate();
        connection.prepareStatement("TRUNCATE TABLE products;").executeUpdate();
        connection.prepareStatement("TRUNCATE TABLE productlines").executeUpdate();
        connection.prepareStatement("TRUNCATE table orders; ").executeUpdate();
        connection.prepareStatement("TRUNCATE table customers; ").executeUpdate();
        connection.prepareStatement("TRUNCATE table employees; ").executeUpdate();
        connection.prepareStatement("TRUNCATE table offices; ").executeUpdate();
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    }

    private Order createOrderForSaveTest(int customerNumber) {
        return Order.builder()
                .orderDate(LocalDate.of(1995, 2, 4))
                .requiredDate(LocalDate.of(1995, 2, 5))
                .shippedDate(LocalDate.of(1995, 2, 6))
                .status("Shipped")
                .comments("test order")
                .customer(createCustomerForSaveTest(customerNumber))
                .build();
    }

    private Customer createCustomerForSaveTest(int customerNumber) {
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


    @Test
    public void given_order_when_save_then_return_saved_order_with_new_unique_number() {
        //Given
        Order order = createOrderForSaveTest(103);

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

        Order order = createOrderForSaveTest(103);

        //when
        Order returnedOrder = sut.save(order);

        //then
        assertThat(returnedOrder.getOrderNumber()).isEqualTo(10100);

    }

    @Test
    public void given_existing_number_when_find_by_number_then_return_order() {
        //given
        Order order = Order.builder()
                .orderNumber(10100)
                .orderDate(LocalDate.of(2003, 1, 10))
                .requiredDate(LocalDate.of(2003, 1, 18))
                .shippedDate(LocalDate.of(2003, 1, 14))
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

    @Test
    public void given_customer_number_when_find_by_customer_then_return_list_of_order() {
        //given
        Order order = Order.builder()
                .orderNumber(10100)
                .orderDate(LocalDate.of(2003, 1, 10))
                .requiredDate(LocalDate.of(2003, 1, 18))
                .shippedDate(LocalDate.of(2003, 1, 14))
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

    @Test(expected = RuntimeException.class)
    public void given_inexistent_foreign_key_when_save_then_throw_exception() {
        //given
        Order order = createOrderForSaveTest(1);

        //when
        sut.save(order);
    }

    @Test
    public void given_existing_order_with_new_data_when_update_then_return_true() {
        //given
        Order order = Order.builder()
                .orderNumber(10100)
                .orderDate(LocalDate.of(2003, 1, 10))
                .requiredDate(LocalDate.of(2003, 1, 18))
                .shippedDate(LocalDate.of(2003, 1, 14))
                .status("Returned")
                .customer(Customer.builder().customerNumber(103).build())
                .orderDetailList(new ArrayList<>())
                .build();

        //when
        boolean isUpdated = sut.update(order);

        //then
        assertThat(isUpdated).isTrue();
    }

    @Test
    public void given_inexistent_order_with_new_data_when_update_then_return_false() {
        //given
        Order order = Order.builder()
                .orderNumber(1)
                .orderDate(LocalDate.of(2003, 1, 9))
                .requiredDate(LocalDate.of(2003, 1, 18))
                .shippedDate(LocalDate.of(2003, 1, 11))
                .status("Shipped")
                .customer(Customer.builder().customerNumber(1).build())
                .orderDetailList(new ArrayList<>())
                .build();

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
