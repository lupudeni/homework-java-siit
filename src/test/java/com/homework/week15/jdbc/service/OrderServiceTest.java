package com.homework.week15.jdbc.service;

import com.homework.week15.jdbc.domain.Customer;
import com.homework.week15.jdbc.domain.Order;
import com.homework.week15.jdbc.repository.CustomerDAO;
import com.homework.week15.jdbc.repository.EmployeeDAO;
import com.homework.week15.jdbc.repository.OrderDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    @Mock
    private OrderDAO orderDAO;
    private CustomerDAO customerDAO;
    private EmployeeDAO employeeDAO;
    private ProductService productService;

    @InjectMocks
    private OrderService sut;


    @Test
    public void given_order_when_save_then_return_order_with_updated_code() {
        //given
        Order order = getCustomOrder(103);
        Order returnedOrder = getCustomOrder(103);
        returnedOrder.setOrderNumber(10101);
        Mockito.when(orderDAO.save(order)).thenReturn(returnedOrder);

        //when
        Order result = sut.save(order);

        //then
        assertThat(result).isEqualTo(returnedOrder);
    }


    @Test
    public void given_number_when_find_order_by_number_then_return_order() {
        //given
        Order order = getExistingOrder();
        int orderNumber = 10100;
        Mockito.when(orderDAO.findByNumber(orderNumber)).thenReturn(order);

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

}
