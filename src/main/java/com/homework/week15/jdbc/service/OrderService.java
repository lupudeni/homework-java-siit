package com.homework.week15.jdbc.service;

import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Order;
import com.homework.week15.jdbc.domain.OrderDetail;
import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.repository.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
public class OrderService {
    private final OrderDAO orderDAO;
    private final CustomerDAO customerDAO;
    private final EmployeeDAO employeeDAO;
    private final ProductService productService;

    public OrderService() {
        this(new OrderDAOImpl(), new CustomerDAOImpl(), new EmployeeDAOImpl(), new ProductService());
    }

    public Order save(Order order) {
        return orderDAO.save(order);
    }

    public Optional<Order> findOrderByNumber(int orderNumber) {
        Order order = orderDAO.findByNumber(orderNumber);
        if (order != null) {
            populateOrderDetailsWithProducts(order);

            Optional<Customer> customerOptional = getCustomer(order);
            customerOptional.ifPresent(customer -> {
                if (customer.getSalesRepEmployee() != null) {
                    int employeeNumber = customer.getSalesRepEmployee().getEmployeeNumber();
                    customer.setSalesRepEmployee(employeeDAO.findByNumber(employeeNumber));
                }
            });
        }
        return Optional.ofNullable(order);
    }

    private void populateOrderDetailsWithProducts(Order order) {
        List<OrderDetail> orderDetails = order.getOrderDetailList();
        orderDetails.forEach(detail -> {
                    String code = detail.getProduct().getProductCode();
                    Optional<Product> productOptional = productService.findByCode(code);
                    productOptional.ifPresent(detail::setProduct);
                }
        );
    }

    private Optional<Customer> getCustomer(Order order) {
        int customerNumber = order.getCustomer().getCustomerNumber();
        Customer customer = customerDAO.findCustomerByNumber(customerNumber);
        if (customer != null) {
            order.setCustomer(customer);
            List<Order> orders = findOrdersByCustomerNumber(customerNumber);
            customer.setOrderList(orders);
        }
        return Optional.ofNullable(customer);
    }


    public List<Order> findOrdersByCustomerNumber(int customerNumber) {
        return orderDAO.findByCustomerNumber(customerNumber);
    }

    public boolean update(Order order) {
        return orderDAO.update(order);
    }

    public boolean delete(int orderNumber) {
        return orderDAO.delete(orderNumber);
    }

}
