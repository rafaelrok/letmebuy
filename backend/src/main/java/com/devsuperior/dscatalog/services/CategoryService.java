package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repository.CategoryRepository;
import com.devsuperior.dscatalog.services.handlers.DataBaseException;
import com.devsuperior.dscatalog.services.handlers.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;
    
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll() {
        List<Category> list = categoryRepo.findAll();
        return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());

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
