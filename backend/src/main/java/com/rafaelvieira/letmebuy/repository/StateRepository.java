package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {

    @Transactional(readOnly = true)
    public List<State> findAllByOrderByName();
}
