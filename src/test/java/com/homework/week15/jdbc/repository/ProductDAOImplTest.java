package com.homework.week15.jdbc.repository;

import com.homework.week15.jdbc.DBUtils;
import com.homework.week15.jdbc.domain.Product;
import com.homework.week15.jdbc.domain.ProductLine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductDAOImplTest {

    private ProductDAO sut;

    @Before
    public void setUp() throws SQLException {
        sut = new ProductDAOImpl("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET");
        DBUtils.setUp();
    }

    @After
    public void cleanUp() throws SQLException {
        DBUtils.cleanUp();
    }

    @Test
    public void given_product_when_save_then_return_product_with_updated_code() {
        //given
        Product product = getCustomeProduct();

        //when
        Product returnedProduct = sut.save(product);

        //then
        assertThat(returnedProduct.getProductCode()).isEqualTo("S10_1950");
    }

    @Test
    public void given_first_product_when_save_then_return_product_with_first_code() throws SQLException {
        //given
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 0; ").executeUpdate();
        connection.prepareStatement("TRUNCATE table products; ").executeUpdate();
        connection.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        Product product = getCustomeProduct();

        //when
        Product returnedProduct = sut.save(product);

        //then
        assertThat(returnedProduct.getProductCode()).isEqualTo("S01_0000");
    }

    @Test
    public void given_last_index_when_save_then_return_product_with_updated_code() throws SQLException {
        //given
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        String products = "INSERT INTO `products`(`productCode`,`productName`,`productLine`,`productScale`,`productVendor`,`productDescription`,`quantityInStock`,`buyPrice`,`MSRP`) VALUES " +
                "('S10_9999','1952 Alpine Renault 1300','Classic Cars','1:10','Classic Metal Creations','Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',7305,'98.58','214.30');\n";
        connection.prepareStatement(products).executeUpdate();

        Product product = getCustomeProduct();

        //when
        Product returnedProduct = sut.save(product);

        //then
        assertThat(returnedProduct.getProductCode()).isEqualTo("S11_0000");
    }

    @Test
    public void given_existing_code_when_find_by_code_then_return_product() {
        //given
       Product product= getExistingProduct();

        String productCode = "S10_1949";

        //when
        Product returnedProduct = sut.findByCode(productCode);

        //then
        assertThat(returnedProduct).isEqualTo(product);

    }

    @Test
    public void given_inexistent_product_code_when_search_by_code_then_return_null() {
        //given
        String productCode = "S30_0000";

        //when
        Product returnedProduct = sut.findByCode(productCode);

        //then
        assertThat(returnedProduct).isNull();
    }

    @Test
    public void given_name_when_search_by_name_then_return_list_of_products() {
        //given
        //1952 Alpine Renault 1300
        String productName = "Alpine";

        //when
        List<Product> products = sut.findByName(productName);

        //then
        assertThat(products).containsExactly(getExistingProduct());
    }

    @Test
    public void given_unknown_name_when_search_by_name_then_return_empty_list() {
        //given
        String productName = "unknown";

        //when
        List<Product> products = sut.findByName(productName);

        //then
        assertThat(products).isEmpty();
    }

    @Test
    public void given_product_when_update_then_return_true() {
        //given
        Product product = getExistingProduct();
        getExistingProduct().setBuyPrice(new BigDecimal("100"));

        //when
        boolean isUpdated = sut.update(product);

        //then
        assertThat(isUpdated).isTrue();
    }

    @Test
    public void given_inexistent_product_code_when_update_then_return_false() {
        //given
        Product product = getCustomeProduct();
        product.setProductCode("unknwon");

        //when
        boolean isUpdated = sut.update(product);

        //then
        assertThat(isUpdated).isFalse();
    }

    @Test
    public void given_productCode_when_delete_then_return_true() throws SQLException {
        //given
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels_test?serverTimezone=EET", "siit", "siit");
        String products = "INSERT INTO `products`(`productCode`,`productName`,`productLine`,`productScale`,`productVendor`,`productDescription`,`quantityInStock`,`buyPrice`,`MSRP`) VALUES " +
                "('S10_9999','1952 Alpine Renault 1300','Classic Cars','1:10','Classic Metal Creations','Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.',7305,'98.58','214.30');\n";
        connection.prepareStatement(products).executeUpdate();
        String productCode = "S10_9999";

        //when
        boolean isDeleted = sut.delete(productCode);

        //then
        assertThat(isDeleted).isTrue();
    }

    @Test
    public void given_unknown_product_code_when_delete_then_return_false() {
        //given
        String productCode = "unknown";

        //when
        boolean isDeleted = sut.delete(productCode);

        //then
        assertThat(isDeleted).isFalse();
    }

    private Product getExistingProduct() {
        return Product.builder()
                .productCode("S10_1949")
                .productName("1952 Alpine Renault 1300")
                .productLine(ProductLine.builder()
                        .productLine("Classic Cars")
                        .textDescription("Attention car enthusiasts: Make your wildest car ownership dreams come true.")
                        .build()
                )
                .productScale("1:10")
                .productVendor("Classic Metal Creations")
                .productDescription("Turnable front wheels; steering function; detailed interior; detailed engine; opening hood; opening trunk; opening doors; and detailed chassis.")
                .quantityInStock(7305)
                .buyPrice(new BigDecimal("98.58"))
                .MSRP(new BigDecimal("214.30"))
                .build();
    }

    private Product getCustomeProduct() {
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
