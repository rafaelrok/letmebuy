package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.City;
import com.rafaelvieira.letmebuy.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository repo;

    public List<City> findByState(Integer estadoId) {
        return repo.findCities(estadoId);
    }
}
