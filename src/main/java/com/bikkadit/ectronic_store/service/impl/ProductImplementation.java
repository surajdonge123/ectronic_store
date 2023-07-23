package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.dto.ProductDto;
import com.bikkadit.ectronic_store.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductImplementation implements ProductService {


    @Override
    public ProductDto createProduct(ProductDto productDto) {
        return null;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String proId) {
        return null;
    }

    @Override
    public void deleteProduct(String proId) {

    }

    @Override
    public List<ProductDto> getAllProduct() {
        return null;
    }

    @Override
    public ProductDto getSingleProduct(String proId) {
        return null;
    }

    @Override
    public List<ProductDto> getByTitle(String subTitle) {
        return null;
    }

    @Override
    public List<ProductDto> getByLive() {
        return null;
    }
}
