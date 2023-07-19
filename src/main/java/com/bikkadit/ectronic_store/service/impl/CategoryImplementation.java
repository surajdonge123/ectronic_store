package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.CategoryDto;
import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.entity.Category;
import com.bikkadit.ectronic_store.exception.ResourceNotFoundException;
import com.bikkadit.ectronic_store.helper.Helper;
import com.bikkadit.ectronic_store.repository.CategoryRepository;
import com.bikkadit.ectronic_store.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryImplementation implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryImplementation.class);

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
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        Category updatedCategory = categoryRepository.save(category);
        CategoryDto dto = modelMapper.map(updatedCategory, CategoryDto.class);
        return dto;

    }

    @Override
    public void deleteCategory(String categoryId) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        categoryRepository.delete(category);

    }

    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort=(sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()):(Sort.by(sortBy).ascending());
        Pageable pageable= PageRequest.of(pageNumber,pageSize,sort);
        Page<Category> page = categoryRepository.findAll(pageable);
        PageableResponse<CategoryDto> response = Helper.getPageableResponse(page, CategoryDto.class);

        return response;
    }

    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        CategoryDto map = modelMapper.map(category, CategoryDto.class);
        return map;
    }
}
