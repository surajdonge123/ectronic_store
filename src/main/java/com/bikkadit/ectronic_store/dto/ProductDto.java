package com.bikkadit.ectronic_store.dto;

import com.bikkadit.ectronic_store.validation.ImageNameValid;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private String productId;
    @NotBlank(message = "Product title should not be Blank")
    @Size(min = 3, max = 255, message = "Product Title should contain at least 3 letters")
    private String productTitle;
    @NotBlank(message = "Brand Name Should not be blank")
    @Size(min = 1, max = 25, message = "Brand Title should contains at least 1 letter and max 25 letters")
    private String Brand;
    @NotBlank(message = "Manufactured name should not be null !!")
    @Size(min = 1, max = 25, message = "Manufacturer company name should contains at least one letters !!")
    private String manufacturedBy;
    @NotBlank(message = "Country name should not be null !!")
    @Size(min = 1, max = 25, message = "Manufacturer country name should contains at least one letters !!")
    private String manufacturedIn;
    @NotBlank(message = "product Description should not be null")
    @Size(min = 15, max = 100000, message = "Description of product should be min of 15 letters")
    private String description;
    private Date addedDate;
    @NotBlank
    @DecimalMin(value = "10", message = "price must be at least 10")
    @DecimalMax(value = "1000000", message = "price must not  Exceed than 1000000")
    private Long price;
    private Integer discountedPrice;
    @NotBlank(message = "Quantity should not null !!")
    private Integer Quantity;
    private boolean live;
    private boolean stock;

    @ImageNameValid
    private String productImage;


}
