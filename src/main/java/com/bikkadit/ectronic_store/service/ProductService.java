package com.bikkadit.ectronic_store.service;

import com.bikkadit.ectronic_store.dto.ProductDto;

import java.util.List;

public interface ProductService {

    public ProductDto createProduct(ProductDto productDto);

    public ProductDto updateProduct(ProductDto productDto,String proId);

    public void deleteProduct(String proId);

    public List<ProductDto> getAllProduct();

    public ProductDto getSingleProduct(String proId);

    List<ProductDto> getByTitle(String subTitle);

    List<ProductDto> getByLive();






}
