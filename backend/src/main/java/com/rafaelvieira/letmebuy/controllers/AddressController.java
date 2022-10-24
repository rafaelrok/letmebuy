package com.rafaelvieira.letmebuy.controllers;

import com.rafaelvieira.letmebuy.client.ViaZipAddress;
import com.rafaelvieira.letmebuy.dto.AddressDTO;
import com.rafaelvieira.letmebuy.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author rafae
 */

@RestController
@RequestMapping(value="/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @Autowired
    private ViaZipAddress viaZipAddress;


    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<Page<AddressDTO>> findAll(Pageable pageable) {
        Page<AddressDTO> list = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{zipcode}", produces = "application/json")
    public ResponseEntity<AddressDTO> findByZipcode(@PathVariable String zipcode) {
        AddressDTO dto = viaZipAddress.fromGetZipcode(zipcode).getBody();
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Integer id) {
        AddressDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<AddressDTO> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
