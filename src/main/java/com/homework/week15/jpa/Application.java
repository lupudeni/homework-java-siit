package com.homework.week15.jpa;

import com.homework.week15.jpa.entity.Product;
import com.homework.week15.jpa.entity.ProductLine;
import com.homework.week15.jpa.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.math.BigDecimal;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@AllArgsConstructor
public class Application implements CommandLineRunner {

    private ApplicationContext applicationContext;

    private static SpringApplication springApplication = new SpringApplication(Application.class);

    public static void main(String[] args) {
        springApplication.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        ProductService productService = applicationContext.getBean(ProductService.class);

        Product product = Product.builder()
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

        productService.save(product);

        productService.delete("S72_3216");

    }
}
