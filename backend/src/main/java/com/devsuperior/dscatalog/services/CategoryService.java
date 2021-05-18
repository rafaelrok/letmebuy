package com.devsuperior.dscatalog.services;

import java.util.List;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repository.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }
}
