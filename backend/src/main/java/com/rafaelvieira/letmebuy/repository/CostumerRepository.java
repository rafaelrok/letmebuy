package com.rafaelvieira.letmebuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rafaelvieira.letmebuy.entities.Costumer;

/**
 * @author rafae
 */
@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long>{

//    @Transactional(readOnly=true)
//    Costumer findByName(String firstName);
}
