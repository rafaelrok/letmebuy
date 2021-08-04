package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.CategoryDTO;
import com.rafaelvieira.letmebuy.dto.ProductDTO;
import com.rafaelvieira.letmebuy.dto.UriDTO;
import com.rafaelvieira.letmebuy.entities.Category;
import com.rafaelvieira.letmebuy.entities.Product;
import com.rafaelvieira.letmebuy.repository.CategoryRepository;
import com.rafaelvieira.letmebuy.repository.ProductRepository;
import com.rafaelvieira.letmebuy.services.handlers.DataBaseException;
import com.rafaelvieira.letmebuy.services.handlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private S3Service s3Service;

    /*
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Pageable pageable) {
        Page<Product> list = productRepo.findAll(pageable);
        return list.map(x -> new ProductDTO(x));
    }
     */

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(Long categoryId, String name, Pageable pageable) {
        List<Category> categories = (categoryId == 0) ? null : Arrays.asList(categoryRepo.getOne(categoryId));
        Page<Product> page = productRepo.find(categories, name, pageable);
        productRepo.findProductsWithCategories(page.getContent());
        return page.map(x -> new ProductDTO(x, x.getCategories()));
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Optional<Product> obj = productRepo.findById(id);
        Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrada"));
        return new ProductDTO(entity, entity.getCategories());
    }

    @Transactional
    public ProductDTO save(ProductDTO dto) {
        Product entity = new Product();
        copyDtoToEntity(dto, entity);
        entity = productRepo.save(entity);
        return new ProductDTO(entity);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            // getOne salva em memoria o objeto para não acessr 2 vezes a base
            Product entity = productRepo.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = productRepo.save(entity);
            return new ProductDTO(entity);

        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Produto não encontrada " + id);
        }
    }

    public void delete(Long id) {
        try {
            productRepo.deleteById(id);
        }
        // Tratamento para verificar a existecia do id no banco
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Produto não encontrada " + id);
        }
        // Tratamento de integridade do banco verifica se exite produto vinculado a essa produto
        catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }

    }
    
    private void copyDtoToEntity(ProductDTO dto, Product entity) {

        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDate(dto.getDate());
        entity.setImgUrl(dto.getImgUrl());
        entity.setPrice(dto.getPrice());

        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            Category category = categoryRepo.getOne(catDTO.getId());
            entity.getCategories().add(category);
        }
    }

    public UriDTO uploadFile(MultipartFile file) {
        URL url = s3Service.uploadFile(file);
        return new UriDTO(url.toString());
    }
}
