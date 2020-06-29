CREATE DATABASE IF NOT EXISTS `classicmodels_test`  DEFAULT CHARACTER SET latin1;

USE `classicmodels_test`;

/*Table structure for table `customers` */

CREATE TABLE IF NOT EXISTS `offices` (
                                         `officeCode` varchar(10) NOT NULL,
                                         `city` varchar(50) NOT NULL,
                                         `phone` varchar(50) NOT NULL,
                                         `addressLine1` varchar(50) NOT NULL,
                                         `addressLine2` varchar(50) DEFAULT NULL,
                                         `state` varchar(50) DEFAULT NULL,
                                         `country` varchar(50) NOT NULL,
                                         `postalCode` varchar(15) NOT NULL,
                                         `territory` varchar(10) NOT NULL,
                                         PRIMARY KEY (`officeCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `offices`(`officeCode`,`city`,`phone`,`addressLine1`,`addressLine2`,`state`,`country`,`postalCode`,`territory`) values
('1','San Francisco','+1 650 219 4782','100 Market Street','Suite 300','CA','USA','94080','NA');

CREATE TABLE IF NOT EXISTS `employees` (
                                           `employeeNumber` int(11) NOT NULL,
                                           `lastName` varchar(50) NOT NULL,
                                           `firstName` varchar(50) NOT NULL,
                                           `extension` varchar(10) NOT NULL,
                                           `email` varchar(100) NOT NULL,
                                           `officeCode` varchar(10) NOT NULL,
                                           `reportsTo` int(11) DEFAULT NULL,
                                           `jobTitle` varchar(50) NOT NULL,
                                           PRIMARY KEY (`employeeNumber`),
                                           KEY `reportsTo` (`reportsTo`),
                                           KEY `officeCode` (`officeCode`),
                                           CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`reportsTo`) REFERENCES `employees` (`employeeNumber`),
                                           CONSTRAINT `employees_ibfk_2` FOREIGN KEY (`officeCode`) REFERENCES `offices` (`officeCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert  into `employees`(`employeeNumber`,`lastName`,`firstName`,`extension`,`email`,`officeCode`,`reportsTo`,`jobTitle`) values

(1002,'Murphy','Diane','x5800','dmurphy@classicmodelcars.com','1',NULL,'President');


CREATE TABLE IF NOT EXISTS `customers` (
                                           `customerNumber` int(11) NOT NULL,
                                           `customerName` varchar(50) NOT NULL,
                                           `contactLastName` varchar(50) NOT NULL,
                                           `contactFirstName` varchar(50) NOT NULL,
                                           `phone` varchar(50) NOT NULL,
                                           `addressLine1` varchar(50) NOT NULL,
                                           `addressLine2` varchar(50) DEFAULT NULL,
                                           `city` varchar(50) NOT NULL,
                                           `state` varchar(50) DEFAULT NULL,
                                           `postalCode` varchar(15) DEFAULT NULL,
                                           `country` varchar(50) NOT NULL,
                                           `salesRepEmployeeNumber` int(11) DEFAULT NULL,
                                           `creditLimit` decimal(10,2) DEFAULT NULL,
                                           PRIMARY KEY (`customerNumber`),
                                           KEY `salesRepEmployeeNumber` (`salesRepEmployeeNumber`),
                                           CONSTRAINT `customers_ibfk_1` FOREIGN KEY (`salesRepEmployeeNumber`) REFERENCES `employees` (`employeeNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `customers`(`customerNumber`,`customerName`,`contactLastName`,`contactFirstName`,`phone`,`addressLine1`,`addressLine2`,`city`,`state`,`postalCode`,`country`,`salesRepEmployeeNumber`,`creditLimit`)
values (103,'Atelier graphique','Schmitt','Carine ','40.32.2555','54, rue Royale',NULL,'Nantes',NULL,'44000','France',1002,'21000.00');


CREATE TABLE IF NOT EXISTS `orders` (
                                        `orderNumber` int(11) NOT NULL,
                                        `orderDate` date NOT NULL,
                                        `requiredDate` date NOT NULL,
                                        `shippedDate` date DEFAULT NULL,
                                        `status` varchar(15) NOT NULL,
                                        `comments` text,
                                        `customerNumber` int(11) NOT NULL,
                                        PRIMARY KEY (`orderNumber`),
                                        KEY `customerNumber` (`customerNumber`),
                                        CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into orders(orderNumber, orderDate, requiredDate, shippedDate, status,  customerNumber)
values (10100,'2003-01-06','2003-01-13','2003-01-10','Shipped',103);

insert into orders(orderNumber, orderDate, requiredDate, shippedDate, status,  customerNumber)
values (10101,'2003-01-09','2003-01-18','2003-01-11','Shipped',103);


CREATE TABLE IF NOT EXISTS `productlines` (
                                              `productLine` varchar(50) NOT NULL,
                                              `textDescription` varchar(4000) DEFAULT NULL,
                                              `htmlDescription` mediumtext,
                                              `image` mediumblob,
                                              PRIMARY KEY (`productLine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `productlines`(`productLine`,`textDescription`,`htmlDescription`,`image`) values

('Classic Cars','Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.',NULL,NULL);


CREATE TABLE IF NOT EXISTS `products` (
                                          `productCode` varchar(15) NOT NULL,
                                          `productName` varchar(70) NOT NULL,
                                          `productLine` varchar(50) NOT NULL,
                                          `productScale` varchar(10) NOT NULL,
                                          `productVendor` varchar(50) NOT NULL,
                                          `productDescription` text NOT NULL,
                                          `quantityInStock` smallint(6) NOT NULL,
                                          `buyPrice` decimal(10,2) NOT NULL,
                                          `MSRP` decimal(10,2) NOT NULL,
                                          PRIMARY KEY (`productCode`),
                                          KEY `productLine` (`productLine`),
                                          CONSTRAINT `products_ibfk_1` FOREIGN KEY (`productLine`) REFERENCES `productlines` (`productLine`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `products`(`productCode`,`productName`,`productLine`,`productScale`,`productVendor`,`productDescription`,`quantityInStock`,`buyPrice`,`MSRP`) values
('S10_1949','1952 Alpine Renault 1300','Classic Cars','1:10','Classic Metal Creations','Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',7305,'98.58','214.30');


CREATE TABLE IF NOT EXISTS `orderdetails` (
                                              `orderNumber` int(11) NOT NULL,
                                              `productCode` varchar(15) NOT NULL,
                                              `quantityOrdered` int(11) NOT NULL,
                                              `priceEach` decimal(10,2) NOT NULL,
                                              `orderLineNumber` smallint(6) NOT NULL,
                                              PRIMARY KEY (`orderNumber`,`productCode`),
                                              KEY `productCode` (`productCode`),
                                              CONSTRAINT `orderdetails_ibfk_1` FOREIGN KEY (`orderNumber`) REFERENCES `orders` (`orderNumber`),
                                              CONSTRAINT `orderdetails_ibfk_2` FOREIGN KEY (`productCode`) REFERENCES `products` (`productCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `orderdetails`(`orderNumber`,`productCode`,`quantityOrdered`,`priceEach`,`orderLineNumber`) values

(10100,'S10_1949',30,'214.30',3);

CREATE TABLE IF NOT EXISTS `payments` (
                                          `customerNumber` int(11) NOT NULL,
                                          `checkNumber` varchar(50) NOT NULL,
                                          `paymentDate` date NOT NULL,
                                          `amount` decimal(10,2) NOT NULL,
                                          PRIMARY KEY (`customerNumber`,`checkNumber`),
                                          CONSTRAINT `payments_ibfk_1` FOREIGN KEY (`customerNumber`) REFERENCES `customers` (`customerNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

insert  into `payments`(`customerNumber`,`checkNumber`,`paymentDate`,`amount`) values

(103,'HQ336336','2004-10-19','6066.78');




