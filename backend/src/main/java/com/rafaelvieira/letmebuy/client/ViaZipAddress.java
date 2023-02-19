package com.rafaelvieira.letmebuy.client;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.rafaelvieira.letmebuy.dto.AddressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author rafae
 */

@RestController
public class ViaZipAddress implements Serializable {

    private static final long serialVersionUID = 6046704732666502085L;

    @GetMapping(value="/getCep/{zipcode}")
    public ResponseEntity<AddressDTO> fromGetZipcode(@PathVariable(name = "zipcode") String zipcode) {

        RestTemplate restTemplate = new RestTemplate();

        String uri = "https://viacep.com.br/ws/{zipcode}/json/";

        Map<String, String> params = new HashMap<String, String>();
        params.put("zipcode", zipcode);

        AddressDTO dto = restTemplate.getForObject(uri, AddressDTO.class, params);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
