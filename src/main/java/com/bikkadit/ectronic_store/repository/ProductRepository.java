package com.bikkadit.ectronic_store.repository;

import com.bikkadit.ectronic_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {



}
