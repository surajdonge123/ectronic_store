package com.bikkadit.ectronic_store.repository;

import com.bikkadit.ectronic_store.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {
}
