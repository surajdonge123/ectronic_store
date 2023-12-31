package com.bikkadit.ectronic_store.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Products")
public class Product {

    @Id
    private String productId;
    private String productTitle;
    private String brand;
    private String manufacturedBy;
    private String manufacturedIn;
    @Column(length = 10000)
    private String description;
    private Date addedDate;
    private Long price;
    private Integer discountedPrice;
    private Integer quantity;
    private boolean live;
    private boolean stock;
    private String productImage;


}
