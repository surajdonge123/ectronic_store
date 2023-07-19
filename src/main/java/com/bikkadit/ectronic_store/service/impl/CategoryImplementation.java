package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.dto.CategoryDto;
import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.repository.CategoryRepository;
import com.bikkadit.ectronic_store.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        return null;


    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        return null;
    }

    @Override
    public void deleteCategory(String categoryId) {

    }

    @Override
    public PageableResponse<CategoryDto> getAll() {
        return null;
    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        return null;
    }
}
