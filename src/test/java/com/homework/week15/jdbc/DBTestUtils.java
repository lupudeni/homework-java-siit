package com.homework.week15.jdbc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.homework.week15.jdbc.constants.DatabaseConstants.CONNECTION_URL;

public class DBTestUtils {

    public static void createTestDBEnvironment() throws IOException, SQLException {
        String query = Files.readString(
                Path.of("src", "test", "java", "com", "homework", "week15", "jdbc", "test_db_structure.sql"));
//        String query = "CREATE DATABASE IF NOT EXISTS `classicmodels_test`  DEFAULT CHARACTER SET latin1;USE `classicmodels_test`;/*Table structure for table `customers` */CREATE TABLE IF NOT EXISTS `customers` (                             `customerNumber` int(11) NOT NULL,                             `customerName` varchar(50) NOT NULL,                             `contactLastName` varchar(50) NOT NULL,                             `contactFirstName` varchar(50) NOT NULL,                             `phone` varchar(50) NOT NULL,                             `addressLine1` varchar(50) NOT NULL,                             `addressLine2` varchar(50) DEFAULT NULL,                             `city` varchar(50) NOT NULL,                             `state` varchar(50) DEFAULT NULL,                             `postalCode` varchar(15) DEFAULT NULL,                             `country` varchar(50) NOT NULL,                             `salesRepEmployeeNumber` int(11) DEFAULT NULL,                             `creditLimit` decimal(10,2) DEFAULT NULL,                             PRIMARY KEY (`customerNumber`),                             KEY `salesRepEmployeeNumber` (`salesRepEmployeeNumber`),                             CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`salesRepEmployeeNumber`) REFERENCES `employees` (`employeeNumber`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `employees` (                             `employeeNumber` int(11) NOT NULL,                             `lastName` varchar(50) NOT NULL,                             `firstName` varchar(50) NOT NULL,                             `extension` varchar(10) NOT NULL,                             `email` varchar(100) NOT NULL,                             `officeCode` varchar(10) NOT NULL,                             `reportsTo` int(11) DEFAULT NULL,                             `jobTitle` varchar(50) NOT NULL,                             PRIMARY KEY (`employeeNumber`),                             KEY `reportsTo` (`reportsTo`),                             KEY `officeCode` (`officeCode`),                             CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`reportsTo`) REFERENCES `employees` (`employeeNumber`),                             CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`officeCode`) REFERENCES `offices` (`officeCode`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `offices` (                           `officeCode` varchar(10) NOT NULL,                           `city` varchar(50) NOT NULL,                           `phone` varchar(50) NOT NULL,                           `addressLine1` varchar(50) NOT NULL,                           `addressLine2` varchar(50) DEFAULT NULL,                           `state` varchar(50) DEFAULT NULL,                           `country` varchar(50) NOT NULL,                           `postalCode` varchar(15) NOT NULL,                           `territory` varchar(10) NOT NULL,                           PRIMARY KEY (`officeCode`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `orderdetails` (                                `orderNumber` int(11) NOT NULL,                                `productCode` varchar(15) NOT NULL,                                `quantityOrdered` int(11) NOT NULL,                                `priceEach` decimal(10,2) NOT NULL,                                `orderLineNumber` smallint(6) NOT NULL,                                PRIMARY KEY (`orderNumber`,`productCode`),                                KEY `productCode` (`productCode`),                                CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`),                                CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `orders` (                          `orderNumber` int(11) NOT NULL,                          `orderDate` date NOT NULL,                          `requiredDate` date NOT NULL,                          `shippedDate` date DEFAULT NULL,                          `status` varchar(15) NOT NULL,                          `comments` text,                          `customerNumber` int(11) NOT NULL,                          PRIMARY KEY (`orderNumber`),                          KEY `customerNumber` (`customerNumber`),                          CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `payments` (                            `customerNumber` int(11) NOT NULL,                            `checkNumber` varchar(50) NOT NULL,                            `paymentDate` date NOT NULL,                            `amount` decimal(10,2) NOT NULL,                            PRIMARY KEY (`customerNumber`,`checkNumber`),                            CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `productlines` (                                `productLine` varchar(50) NOT NULL,                                `textDescription` varchar(4000) DEFAULT NULL,                                `htmlDescription` mediumtext,                                `image` mediumblob,                                PRIMARY KEY (`productLine`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;CREATE TABLE IF NOT EXISTS `products` (                            `productCode` varchar(15) NOT NULL,                            `productName` varchar(70) NOT NULL,                            `productLine` varchar(50) NOT NULL,                            `productScale` varchar(10) NOT NULL,                            `productVendor` varchar(50) NOT NULL,                            `productDescription` text NOT NULL,                            `quantityInStock` smallint(6) NOT NULL,                            `buyPrice` decimal(10,2) NOT NULL,                            `MSRP` decimal(10,2) NOT NULL,                            PRIMARY KEY (`productCode`),                            KEY `productLine` (`productLine`),                            CONSTRAINT `products_ibfk_1` FOREIGN KEY (`productLine`) REFERENCES `productlines` (`productLine`)) ENGINE=InnoDB DEFAULT CHARSET=latin1;";

        PreparedStatement preparedStatement = getPreparedStatement(query);
        preparedStatement.execute();
        closeConnection(preparedStatement);
    }

    private static PreparedStatement getPreparedStatement(String query) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?serverTimezone=EET", "siit", "siit");
            return connection.prepareStatement(query);
        } catch (SQLException e) {
            System.out.println("Error while getting connection");
            throw new RuntimeException("Error while getting connection: ", e);
        }
    }

    private static void closeConnection(PreparedStatement preparedStatement) {
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("Error while closing connection");
        }
    }

}
