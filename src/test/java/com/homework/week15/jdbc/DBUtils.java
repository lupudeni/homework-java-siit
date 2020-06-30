package com.homework.week15.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {

    public static void setUp() throws SQLException {
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
                "('Classic Cars','Attention car enthusiasts: Make your wildest car ownership dreams come true.',NULL,NULL);";

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

    public static void cleanUp() throws SQLException {
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
}
