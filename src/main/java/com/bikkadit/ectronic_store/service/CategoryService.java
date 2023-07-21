package com.bikkadit.ectronic_store.service;

import com.bikkadit.ectronic_store.dto.CategoryDto;
import com.bikkadit.ectronic_store.helper.PageableResponse;

public interface CategoryService {

    //Create
    CategoryDto createCategory(CategoryDto categoryDto);

    //Update
    CategoryDto updateCategory(CategoryDto categoryDto,String categoryId);

    //Delete
    void deleteCategory(String categoryId);

    //get all
    PageableResponse<CategoryDto> getAll(int pageNumber,int pageSize,String sortBy,String sortDir);


    //get single category
    CategoryDto getSingleCategory(String categoryId);


}
