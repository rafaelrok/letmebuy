package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.City;
import com.rafaelvieira.letmebuy.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafae
 */
@Service
public class CityService {

    private final CityRepository repo;

    public CityService(CityRepository repo) {
        this.repo = repo;
    }

    public List<City> findByState(Integer estadoId) {
        return repo.findCities(estadoId);
    }
}
