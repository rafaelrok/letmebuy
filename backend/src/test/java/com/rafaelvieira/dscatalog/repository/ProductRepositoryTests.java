package com.rafaelvieira.dscatalog.repository;

import com.rafaelvieira.dscatalog.entities.Product;
import com.rafaelvieira.dscatalog.tests.Factory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;


@DataJpaTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository repository;

    private long exintingId;
    private long nonexistintingId;
    private long countTotalProducts;

    @BeforeEach
    void setUp() throws Exception{
        exintingId=1L;
        nonexistintingId=1000L;
        countTotalProducts = 25L;
        
    }

    @Test
    void saveShouldPersistWithAutoincrementWhenIdIsNull() {

        Product product = Factory.createProduct();
        product.setId(null);
        
        product = repository.save(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(countTotalProducts + 1, product.getId());
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists(){

        //Teste de deleta basico quando id existe
        repository.deleteById(exintingId);
        Optional<Product> result = repository.findById(exintingId);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){

        //Teste com produto que existe na base
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(nonexistintingId);
        });
    }
}
