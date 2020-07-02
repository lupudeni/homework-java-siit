package com.homework.week15.jpa.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(generator = "code_sequence")
    @GenericGenerator(
            name = "code_sequence",
            strategy = "com.homework.week15.jpa.utils.ProductCodeSequenceGenerator"
    )
    private String productCode;

    private String productName;

    private String productScale;

    private String productVendor;

    private String productDescription;

    private int quantityInStock;

    private BigDecimal buyPrice;

    private BigDecimal MSRP;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productLine", referencedColumnName = "productLine")
    @ToString.Exclude
    private ProductLine productLine;
}
