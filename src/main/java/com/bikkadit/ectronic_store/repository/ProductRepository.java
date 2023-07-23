package com.bikkadit.ectronic_store.repository;

import com.bikkadit.ectronic_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {


    //Search methods
    List<Product> findByTitleContaining(String subTitle);

    List<Product> findByLiveTrue();


}
