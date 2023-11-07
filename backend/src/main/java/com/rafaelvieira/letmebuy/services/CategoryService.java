package com.rafaelvieira.letmebuy.services;

//#region Imports

import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import com.rafaelvieira.letmebuy.repository.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelvieira.letmebuy.dto.CategoryDTO;
import com.rafaelvieira.letmebuy.entities.Category;
import com.rafaelvieira.letmebuy.services.handlers.DataBaseException;
import com.rafaelvieira.letmebuy.services.handlers.ResourceNotFoundException;
//#endregion

@Service
public class CategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> list = categoryRepo.findAll(pageable);
        return list.map(CategoryDTO::new);

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepo.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO save(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = categoryRepo.save(entity);
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO update(Long id, CategoryDTO dto) {
        try {
            Category entity = categoryRepo.getReferenceById(id);
            entity.setName(dto.getName());
            entity = categoryRepo.save(entity);
            return new CategoryDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Category not found " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new ResourceNotFoundException("Category not found " + id);
        }
        try {
            categoryRepo.deleteById(id);
        }
        catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }
}
