package com.rafaelvieira.letmebuy.services;

import com.rafaelvieira.letmebuy.entities.State;
import com.rafaelvieira.letmebuy.repository.StateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author rafae
 */
@Service
public class StateService {

    private final StateRepository repo;

    public StateService(StateRepository repo) {
        this.repo = repo;
    }

    public List<State> findAll() {
        return repo.findAllByOrderByName();
    }
}
