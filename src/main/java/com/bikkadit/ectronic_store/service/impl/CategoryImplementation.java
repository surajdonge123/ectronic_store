package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.CategoryDto;
import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.entity.Category;
import com.bikkadit.ectronic_store.exception.ResourceNotFoundException;
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

        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCategory = categoryRepository.save(category);
        CategoryDto dto = modelMapper.map(savedCategory, CategoryDto.class);

        return null;


    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId)
    {
        /*Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        CategoryDto dto = modelMapper.map(updatedCategory, CategoryDto.class);
        return dto;*/

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
