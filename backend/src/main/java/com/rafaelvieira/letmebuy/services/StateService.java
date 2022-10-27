package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.State;
import com.rafaelvieira.letmebuy.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafae
 */
@Service
public class StateService {

    @Autowired
    private StateRepository repo;

    public List<State> findAll() {
        return repo.findAllByOrderByName();
    }
}
