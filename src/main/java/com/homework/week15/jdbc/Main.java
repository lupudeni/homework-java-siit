package com.homework.week15.jdbc;

import com.homework.week15.jdbc.domain.*;
import com.homework.week15.jdbc.repository.OrderDAO;
import com.homework.week15.jdbc.repository.OrderDAOImpl;
import com.homework.week15.jdbc.repository.ProductDAO;
import com.homework.week15.jdbc.repository.ProductDAOImpl;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAOImpl();
        OrderDAO orderDAO = new OrderDAOImpl();


        ProductLine productLine = ProductLine.builder()
                .productLine("Planes")
                .build();
        Product product = Product.builder()
                .productDescription("bla bla")
                .productLine(productLine)
                .productName("productName")
                .productScale("1:2")
                .productVendor("productVendor")
                .buyPrice(new BigDecimal("1.5"))
                .MSRP(new BigDecimal("12.5"))
                .quantityInStock(1)
                .build();

//        productDAO.save(product);

//        String code = "S72_3212";
//       Product findProdct = productDAO.findByCode(code);
//        System.out.println(findProdct);

//        String name = "Ford";
//
//        List<Product> products = productDAO.findByName(name);
//        products.forEach(System.out::println);

        productDAO.delete("000");

        Office office = Office.builder()
                .officeCode("10")
                .city("city")
                .phone("phone00")
                .addressLine1("address")
//                .addressLine2() //can be null
//                .state()//can be null
                .country("country")
                .postalCode("postal")
                .territory("teritory")
                .build();

        Employee employee = Employee.builder()
                .employeeNumber(1)
                .lastName("lastname")
                .firstName("firstname")
                .extension("ext")
                .email("email")
                .office(office) // make office
//        .reportsTo() //can be null thank baby jesus
                .jobTitle("jobtitle")
                .build();

        Customer customer = Customer.builder()
                .customerNumber(1)
                .customerName("cusname")
                .contactLastName("cuslastname")
                .contactFirstName("fisrtname")
                .phone("09phone")
                .addressLine1("address")
//                .addressLine2()
                .city("city")
//                .state()
//                .postalCode()
//                .salesRepEmployee(employee) //make employee
//                .creditLimit()
                .build();


        Order order = Order.builder()
                .orderNumber(1)
                .orderDate(LocalDate.of(1995, 2, 4))
                .requiredDate(LocalDate.of(1995, 3, 4))
//            .shippedDate()
                .status("status")
//            .comments()
                .customer(customer)
                .build();
    }
}
