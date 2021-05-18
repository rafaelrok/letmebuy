package com.devsuperior.dscatalog.controllers;

import java.util.List;

import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService serviceCat;
    
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = serviceCat.findAll();
        return ResponseEntity.ok().body(list);
        
    }
}
