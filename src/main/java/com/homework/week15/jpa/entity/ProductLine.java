package com.homework.week15.jpa.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "productlines")
public class ProductLine {
    @Id
    private String productLine;

    private String textDescription;

    private String htmlDescription;

    private byte[] image;

    @ToString.Exclude
    @OneToMany(mappedBy = "productLine")
    private List<Product> productList;

}
