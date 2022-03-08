package com.rafaelvieira.letmebuy.services;

//#region Imports

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.rafaelvieira.letmebuy.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelvieira.letmebuy.dto.CategoryDTO;
import com.rafaelvieira.letmebuy.entities.Category;
import com.rafaelvieira.letmebuy.services.handlers.DataBaseException;
import com.rafaelvieira.letmebuy.services.handlers.ResourceNotFoundException;
//#endregion

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAllPaged(Pageable pageable) {
        Page<Category> list = categoryRepo.findAll(pageable);
        return list.map(x -> new CategoryDTO(x));

    }

    @Transactional(readOnly = true)
    public CategoryDTO findById(Long id) {
        Optional<Category> obj = categoryRepo.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada"));
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
            // getOne salva em memoria o objeto para não acessr 2 vezes a base
            Category entity = categoryRepo.getOne(id);
            entity.setName(dto.getName());
            entity = categoryRepo.save(entity);
            return new CategoryDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Categoria não encontrada " + id);
        }
    }

    public void delete(Long id) {
        try {
            categoryRepo.deleteById(id);
        }
        // Tratamento para verificar a existecia do id no banco
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Categoria não encontrada " + id);
        }
        // Tratamento de integridade do banco verifica se exite produto vinculado a essa categoria
        catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
        
    }
}
