package com.rafaelvieira.dscatalog.controller;

import com.rafaelvieira.dscatalog.controllers.ProductController;
import com.rafaelvieira.dscatalog.dto.ProductDTO;
import com.rafaelvieira.dscatalog.services.ProductService;
import com.rafaelvieira.dscatalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;

    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;

    @BeforeEach
    void setUp() throws Exception{

        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));
        when(service.findAllPaged(any())).thenReturn(page);
    }

    @Test
    public void findAllShouldReturnPage() throws Exception{

        //Aplicando a chama separada e não apenas em uma linha
        ResultActions result =mockMvc.perform(get("/products")
                        .accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
    }

}
