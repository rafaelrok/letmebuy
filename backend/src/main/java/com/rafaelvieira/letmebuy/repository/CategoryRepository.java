package com.rafaelvieira.letmebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.rafaelvieira.letmebuy.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
