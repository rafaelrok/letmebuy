package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.dto.AddressDTO;
import com.rafaelvieira.letmebuy.dto.CategoryDTO;
import com.rafaelvieira.letmebuy.dto.ProductDTO;
import com.rafaelvieira.letmebuy.entities.Address;
import com.rafaelvieira.letmebuy.entities.Category;
import com.rafaelvieira.letmebuy.entities.Product;
import com.rafaelvieira.letmebuy.repository.AddressRepository;
import com.rafaelvieira.letmebuy.services.handlers.DataBaseException;
import com.rafaelvieira.letmebuy.services.handlers.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * @author rafae
 */

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public Page<AddressDTO> findAllPaged(Pageable pageable) {
        Page<Address> list = addressRepository.findAll(pageable);
        return list.map(AddressDTO::new);
    }


    @Transactional(readOnly = true)
    public AddressDTO findById(Integer id) {
        Optional<Address> obj = addressRepository.findById(id);
        Address entity = obj.orElseThrow(() -> new ResourceNotFoundException("Endereço não Cadastrado"));
        return new AddressDTO(entity);
    }

    @Transactional(readOnly = true)
    public AddressDTO findByZipcode(String zipcode) {
        Optional<Address> obj = addressRepository.findByZipcode(zipcode);
        Address entity = obj.orElseThrow(() -> new ResourceNotFoundException("Endereço não localizado"));
        return new AddressDTO(entity);
    }

    public void delete(Integer id) {
        try {
            addressRepository.deleteById(id);
        }
        // Tratamento para verificar a existential do id no banco
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Endereço não Cadastrado " + id);
        }
        // Tratamento de integridade do banco verifica se exite produto vinculado a essa categoria
        catch (DataIntegrityViolationException ex) {
            throw new DataBaseException("Integrity Violation");
        }
    }


}
