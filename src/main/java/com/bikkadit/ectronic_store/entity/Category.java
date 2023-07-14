package com.bikkadit.ectronic_store.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "categories")
public class Category {

    @Id
    private String categoryId;
    @Column(name = "category_title")
    private String title;
    @Column(name = "category_description")
    private String description;
    private String coverImage;

}
