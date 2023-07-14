package com.bikkadit.ectronic_store.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    private String categoryId;
    private String title;
    private String description;
    private String coverImage;

}
