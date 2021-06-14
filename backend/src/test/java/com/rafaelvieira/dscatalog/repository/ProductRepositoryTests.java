package com.rafaelvieira.dscatalog.repository;

import com.rafaelvieira.dscatalog.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;


@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){

        long exintingId = 1L;
        repository.deleteById(exintingId);
        Optional<Product> result = repository.findById(exintingId);
        Assertions.assertFalse(result.isPresent());
    }
}
