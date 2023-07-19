package com.bikkadit.ectronic_store.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CategoryDto {

    private String categoryId;

    @NotBlank
    @Size(min = 3,message = "Invalid Title !!. Title size should be more than 3 characters")
    private String title;

    @NotBlank
    @Size(min = 10,message ="invalid Description !! Description size should be more than 10 characters " )
    private String description;

    private String coverImage;

}
