package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.CategoryDto;
import com.bikkadit.ectronic_store.dto.UserDto;
import com.bikkadit.ectronic_store.helper.PageableResponse;
import com.bikkadit.ectronic_store.entity.Category;
import com.bikkadit.ectronic_store.exception.ResourceNotFoundException;
import com.bikkadit.ectronic_store.helper.Helper;
import com.bikkadit.ectronic_store.repository.CategoryRepository;
import com.bikkadit.ectronic_store.service.CategoryService;
import com.bikkadit.ectronic_store.service.FileService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class CategoryImplementation implements CategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryImplementation.class);
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${category.profile.image.path}")
    private String imagePath;


    /**
     * @param categoryDto
     * @return dto
     * @Author Suraj
     * @apiNote Logic for Create  category
     */
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        logger.info("Initialising request for generating String categoryId ");
        String categoryId = UUID.randomUUID().toString();
        logger.info(" Request Complete for generating categoryId {}" + categoryId);
        categoryDto.setCategoryId(categoryId);
        Category category = modelMapper.map(categoryDto, Category.class);
        logger.info("request start for creating Category");
        Category savedCategory = categoryRepository.save(category);
        logger.info("request complete for creating Category {}" + savedCategory);
        CategoryDto dto = modelMapper.map(savedCategory, CategoryDto.class);
        return dto;
    }

    /**
     * @param categoryDto
     * @param categoryId
     * @return dto
     * @throws ResourceNotFoundException
     * @apiNote Logic for Update category
     */
    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, String categoryId) {
        logger.info("Initialising request for getSingleId");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("request complete for getSingleId {}" + category);
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());
        logger.info("Request Start for Update Category");
        Category updatedCategory = categoryRepository.save(category);
        logger.info("Request completed for update category {}" + updatedCategory);
        CategoryDto dto = modelMapper.map(updatedCategory, CategoryDto.class);
        return dto;
    }

    /**
     * @param categoryId
     * @throws ResourceNotFoundException
     * @apiNote Logic for deleteCategory
     */
    @Override
    public void deleteCategory(String categoryId) {
        logger.info("Initialising request for delete category for " + categoryId);
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("Initialising request completed for delete category for" + categoryId);
        categoryRepository.delete(category);

        //for delete userImage
        String fullPath = imagePath + category.getCoverImage();
        try {
            Path path= Paths.get(fullPath);
            Files.delete(path);
        }catch (NoSuchFileException e){
            logger.info("No user image found in folder");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }


    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return response
     * @apiNote logic for get all category
     * @Author Suraj
     */
    @Override
    public PageableResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        logger.info("Initialising request for getAll category ");
        Page<Category> page = categoryRepository.findAll(pageable);
        logger.info("Request complete for getAll category ");
        PageableResponse<CategoryDto> response = Helper.getPageableResponse(page, CategoryDto.class);
        return response;
    }

    /**
     * @param categoryId
     * @return map
     * @Author Suraj
     * @apiNote logic for getSingleCategory
     */
    @Override
    public CategoryDto getSingleCategory(String categoryId) {
        logger.info("Initialising request for getSingleId");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND));
        logger.info("request complete for getSingleId {}" + category);
        CategoryDto map = modelMapper.map(category, CategoryDto.class);
        return map;
    }


}
