package com.bikkadit.ectronic_store.controller;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.CategoryDto;
import com.bikkadit.ectronic_store.dto.PageableResponse;
import com.bikkadit.ectronic_store.helper.ApiResponse;
import com.bikkadit.ectronic_store.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private CategoryService categoryService;

    /**
     * @param categoryDto
     * @return category
     * @Author Suraj
     * @apiNote Api for CreateCategory
     */
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        logger.info("Initialising Request for createCategory ");
        CategoryDto category = categoryService.createCategory(categoryDto);
        logger.info("Request complete for createCategory {}" + category);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.CREATED);
    }

    /**
     * @param categoryDto
     * @param categoryId
     * @return dto
     * @apiNote Api update Category
     */
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
        logger.info("Initialising Request for Update category {}" + categoryId);
        CategoryDto dto = categoryService.updateCategory(categoryDto, categoryId);
        logger.info("Request complete for updateCategory {}" + dto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    /**
     * @param catId
     * @return response
     * @apiNote api for delete category
     */
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable String catId) {
        logger.info("Initialising Request for delete category {}" + catId);
        categoryService.deleteCategory(catId);
        logger.info("Request complete for deleteCategory {} " + catId);
        ApiResponse response = ApiResponse.builder().message(AppConstant.USER_DELETE).success(true).status(HttpStatus.OK).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return allCategory
     * @Author Suraj
     * @apiNote getAllCategory
     */
    @GetMapping("/")
    public ResponseEntity<PageableResponse<CategoryDto>> getAllCategory(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        logger.info("Initialising Request for getAllCategory");
        PageableResponse<CategoryDto> allCategory = categoryService.getAll(pageNumber, pageSize, sortBy, sortDir);
        logger.info("Request complete for getAllCategory ");
        return new ResponseEntity<>(allCategory, HttpStatus.OK);
    }

    /**
     * @param catId
     * @return
     * @apiNote api for getSingleCategory
     */
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable String catId) {
        logger.info("Initialising Request for getSingleCategory category {}" + catId);
        CategoryDto singleCategory = categoryService.getSingleCategory(catId);
        logger.info("Request complete for getSingleCategory {} " + catId);
        return new ResponseEntity<>(singleCategory, HttpStatus.OK);
    }

}
