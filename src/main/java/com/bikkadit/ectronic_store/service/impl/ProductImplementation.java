package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.ProductDto;
import com.bikkadit.ectronic_store.entity.Product;
import com.bikkadit.ectronic_store.exception.ResourceNotFoundException;
import com.bikkadit.ectronic_store.repository.ProductRepository;
import com.bikkadit.ectronic_store.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductImplementation implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        String proId = UUID.randomUUID().toString();
        productDto.setProductId(proId);
        Product product = modelMapper.map(productDto, Product.class);
        Product savedProduct = this.productRepository.save(product);
        ProductDto dto = modelMapper.map(savedProduct, ProductDto.class);
        return dto;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, String proId) {

        Product product = productRepository.findById(proId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        product.setProductTitle(productDto.getProductTitle());
        product.setDescription(product.getDescription());
        product.setBrand(productDto.getBrand());
        product.setDiscountedPrice(product.getDiscountedPrice());
        product.setLive(productDto.isLive());
        product.setManufacturedBy(productDto.getManufacturedBy());
        product.setManufacturedIn(productDto.getManufacturedIn());
        product.setPrice(productDto.getPrice());
        product.setLive(productDto.isLive());
        product.setQuantity(productDto.getQuantity());
        Product savedProduct = productRepository.save(product);
        ProductDto dto = modelMapper.map(savedProduct, ProductDto.class);
        
        return dto;
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
