package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
