package com.bikkadit.ectronic_store.service;

import com.bikkadit.ectronic_store.dto.CategoryDto;

public interface CategoryService {

    //Create
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update
    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);

    //Delete
    void deleteCategory(String categoryId);
    //get single


    //get all



}
