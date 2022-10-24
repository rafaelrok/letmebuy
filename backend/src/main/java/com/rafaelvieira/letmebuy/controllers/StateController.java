package com.rafaelvieira.letmebuy.controllers;

import com.rafaelvieira.letmebuy.dto.CityDTO;
import com.rafaelvieira.letmebuy.dto.StateDTO;
import com.rafaelvieira.letmebuy.entities.City;
import com.rafaelvieira.letmebuy.entities.State;
import com.rafaelvieira.letmebuy.services.CityService;
import com.rafaelvieira.letmebuy.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rafae
 */
@RestController
@RequestMapping(value="/states")
public class StateController {

    @Autowired
    private StateService service;

    @Autowired
    private CityService cityService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll() {
        List<State> list = service.findAll();
        List<StateDTO> listDto = list.stream().map(StateDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/{stateId}/cities", method=RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId) {
        List<City> list = cityService.findByState(stateId);
        List<CityDTO> listDto = list.stream().map(CityDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }
}
