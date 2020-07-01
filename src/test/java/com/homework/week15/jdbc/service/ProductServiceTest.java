package com.homework.week15.jdbc.service;

import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.domain.ProductLine;
import com.homework.week15.jdbc.repository.ProductDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private  ProductDAO productDAO; // = new ProductDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");

    @InjectMocks
    private ProductService sut;

    @Test
    public void given_product_when_save_then_return_product_with_new_code() {
        //given
        Product product = getCustomProduct();
        Product returnedProduct = getCustomProduct();
        returnedProduct.setProductCode("S10_1950");

        //when
        Mockito.when(productDAO.save(product)).thenReturn(returnedProduct);
        Optional<Product> returnedOptional = sut.save(product);

        //then
        assertThat(returnedOptional).isEqualTo(Optional.of(returnedProduct));

    }

    private Product getCustomProduct() {
        return Product.builder()
                .productName("1996 Moto Guzzi 1100i")
                .productLine(ProductLine.builder()
                        .productLine("Classic Cars")
                        .textDescription("Attention car enthusiasts: Make your wildest car ownership dreams come true.")
                        .build()
                )
                .productScale("1:10")
                .productVendor("Classic Metal Creations")
                .productDescription("Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.")
                .quantityInStock(10)
                .buyPrice(new BigDecimal("100.58"))
                .MSRP(new BigDecimal("214.30"))
                .build();
    }
}
