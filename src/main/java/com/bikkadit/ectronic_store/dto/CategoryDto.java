package com.bikkadit.ectronic_store.dto;


import com.bikkadit.ectronic_store.validation.ImageNameValid;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryDto {

    private String categoryId;

    @NotBlank(message = "Title should not be null")
    @Size(min = 3,message = "Invalid Title !!. Title size should be more than 3 characters")
    private String title;

    @NotBlank(message = "Description should not be null")
    @Size(min = 10,message ="invalid Description !! Description size should be more than 10 characters " )
    private String description;

    @NotBlank
    @ImageNameValid
    private String coverImage;

}
