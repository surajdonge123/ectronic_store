package com.bikkadit.ectronic_store.service;

import com.bikkadit.ectronic_store.dto.ProductDto;
import com.bikkadit.ectronic_store.helper.PageableResponse;

import java.util.List;

public interface ProductService {

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto updateProduct(ProductDto productDto,String proId);

    public void deleteProduct(String proId);

    PageableResponse<ProductDto> getAllProduct(int pageNumber,int pageSize,String sortBy,String sortDir);

    ProductDto  getSingleProduct(String proId);

    PageableResponse<ProductDto> getByProductTitle(String subTitle,int pageNumber,int pageSize,String sortBy,String sortDir);

    PageableResponse<ProductDto> getByLive(int pageNumber,int pageSize,String sortBy,String sortDir);






}
