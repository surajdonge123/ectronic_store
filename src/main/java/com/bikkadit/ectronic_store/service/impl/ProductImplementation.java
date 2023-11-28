package com.bikkadit.ectronic_store.service.impl;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.dto.ProductDto;
import com.bikkadit.ectronic_store.entity.Product;
import com.bikkadit.ectronic_store.exception.ResourceNotFoundException;
import com.bikkadit.ectronic_store.helper.Helper;
import com.bikkadit.ectronic_store.helper.PageableResponse;
import com.bikkadit.ectronic_store.repository.ProductRepository;
import com.bikkadit.ectronic_store.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ProductImplementation implements ProductService {

    private Logger logger= LoggerFactory.getLogger(ProductImplementation.class);


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ModelMapper modelMapper;

    @Value("${product.profile.image.path}")
    private String imageUploadPath;

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
        product.setProductImage(productDto.getProductImage());
        Product savedProduct = productRepository.save(product);
        ProductDto dto = modelMapper.map(savedProduct, ProductDto.class);
        return dto;
    }

    @Override
    public void deleteProduct(String proId) {

        Product product = productRepository.findById(proId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        productRepository.delete(product);

        String fullPath = imageUploadPath + product.getProductImage();

        try {
            Path path = Paths.get(fullPath);
            Files.delete(path);
        } catch (NoSuchFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public ProductDto getSingleProduct(String proId) {
        Product product = this.productRepository.findById(proId).orElseThrow(() -> new ResourceNotFoundException(AppConstant.PRODUCT_NOT_FOUND));
        ProductDto dto = modelMapper.map(product, ProductDto.class);
        return dto;
    }

    @Override
    public PageableResponse<ProductDto> getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> allProducts = this.productRepository.findAll(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(allProducts, ProductDto.class);
        return pageableResponse;
    }


    @Override
    public PageableResponse<ProductDto> getByProductTitle(String subTitle, int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> titleContaining = this.productRepository.findByProductTitleContaining(subTitle, pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(titleContaining, ProductDto.class);
        return pageableResponse;
    }

    @Override
    public PageableResponse<ProductDto> getByLive(int pageNumber, int pageSize, String sortBy, String sortDir) {
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> byLiveTrue = this.productRepository.findByLiveTrue(pageable);
        PageableResponse<ProductDto> pageableResponse = Helper.getPageableResponse(byLiveTrue, ProductDto.class);
        return pageableResponse;
    }

}