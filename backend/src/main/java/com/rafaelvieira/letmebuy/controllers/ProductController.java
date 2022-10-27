package com.rafaelvieira.letmebuy.controllers;

import com.rafaelvieira.letmebuy.dto.ProductDTO;
import com.rafaelvieira.letmebuy.dto.UriDTO;
import com.rafaelvieira.letmebuy.entities.Product;
import com.rafaelvieira.letmebuy.services.ProductService;
import com.rafaelvieira.letmebuy.utils.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author rafae
 */

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value="nome", defaultValue="") String nome,
            @RequestParam(value="categories", defaultValue="") String categories,
            @RequestParam(value="page", defaultValue="0") Integer page,
            @RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage,
            @RequestParam(value="orderBy", defaultValue="nome") String orderBy,
            @RequestParam(value="direction", defaultValue="ASC") String direction) {
        String nomeDecoded = URL.decodeParam(nome);
        List<Long> ids = URL.decodeIntList(categories);
        Page<Product> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);
        return ResponseEntity.ok().body(listDto);
    }

//    @GetMapping
//    public ResponseEntity<Page<ProductDTO>> findAll(
//            @RequestParam(value = "categoryId", defaultValue = "0") Long categoryId,
//            @RequestParam(value = "name", defaultValue = "") String name,
//            Pageable pageable) {
//
//        Page<ProductDTO> list = service.findAllPagedWithFeedbacks(categoryId, name.trim(), pageable);
//        return ResponseEntity.ok().body(list);
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        ProductDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {
        dto = service.save(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PostMapping(value = "/image")
    public ResponseEntity<UriDTO> uploadImage(@RequestParam("file") MultipartFile file) {
        UriDTO dto = service.uploadFile(file);
        return ResponseEntity.ok().body(dto);
    }

    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @Valid @RequestBody ProductDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ProductDTO> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
