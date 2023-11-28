package com.bikkadit.ectronic_store.controller;

import com.bikkadit.ectronic_store.constant.AppConstant;
import com.bikkadit.ectronic_store.constant.PaginationConstant;
import com.bikkadit.ectronic_store.dto.ProductDto;
import com.bikkadit.ectronic_store.helper.ApiResponse;
import com.bikkadit.ectronic_store.helper.FileResponse;
import com.bikkadit.ectronic_store.helper.PageableResponse;
import com.bikkadit.ectronic_store.service.FileService;
import com.bikkadit.ectronic_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/products")
public class productController {


    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;


    @Value("${product.profile.image.path}")
    private String ImageUploadPath;


    @PostMapping("/")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        ProductDto product = this.productService.createProduct(productDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping("/update/{proId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable String proId) {
        ProductDto dto = this.productService.updateProduct(productDto, proId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{proId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable String proId) {

        this.productService.deleteProduct(proId);
        ApiResponse apiResponse = ApiResponse.builder().message(AppConstant.PRODUCT_DELETE).status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/{proId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable String proId) {

        ProductDto singleProduct = productService.getSingleProduct(proId);
        return new ResponseEntity<>(singleProduct, HttpStatus.OK);
    }


    @GetMapping("/")
    public ResponseEntity<PageableResponse<ProductDto>> getAll(
            @RequestParam(value = "pageNumber", defaultValue = PaginationConstant.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PaginationConstant.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationConstant.SORT_BY_PRODUCT, required = false) String SortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationConstant.SORT_DIR, required = false) String sortDir) {
        PageableResponse<ProductDto> allProduct = productService.getAllProduct(pageNumber, pageSize, SortBy, sortDir);
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    /**
     * @Author Suraj
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLiveProduct(
            @RequestParam(value = "pageNumber", defaultValue = PaginationConstant.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PaginationConstant.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationConstant.SORT_BY_PRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationConstant.SORT_DIR, required = false) String sortDir) {
        PageableResponse<ProductDto> byLive = productService.getByLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(byLive, HttpStatus.OK);
    }

    /**
     * @Author Suraj
     * @param query
     * @param pageNumber
     * @param pageSize
     * @param sortBy
     * @param sortDir
     * @return
     */
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchProduct(
            @PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = PaginationConstant.PAGE_NUMBER, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = PaginationConstant.PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PaginationConstant.SORT_BY_PRODUCT, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PaginationConstant.SORT_DIR, required = false) String sortDir) {
        PageableResponse<ProductDto> byTitle = productService.getByProductTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(byTitle, HttpStatus.OK);
    }


    /**
     * @Author Suraj
     * @param file
     * @param proId
     * @return
     * @throws IOException
     */
    @PostMapping("/image/{proId}")
    public ResponseEntity<FileResponse> uploadImage(@RequestParam("productImage") MultipartFile file, @PathVariable String proId) throws IOException {

        String uploadFile = fileService.uploadFile(file, ImageUploadPath);
        ProductDto singleProduct = productService.getSingleProduct(proId);


        singleProduct.setProductImage(uploadFile);
        ProductDto productDto = productService.updateProduct(singleProduct, proId);

        FileResponse fileResponse = FileResponse.builder().imageName(uploadFile).message(AppConstant.FILE_UPLOAD).status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(fileResponse, HttpStatus.OK);


    }

    @GetMapping("/image/{proId}")
    public void serveImage(@PathVariable String proId, HttpServletResponse response) throws IOException {

        ProductDto product = productService.getSingleProduct(proId);
        InputStream resource = fileService.getResource(ImageUploadPath, product.getProductImage());

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());

    }


}
