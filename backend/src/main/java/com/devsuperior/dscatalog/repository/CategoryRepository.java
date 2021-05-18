package com.devsuperior.dscatalog.repository;

import com.devsuperior.dscatalog.entities.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
