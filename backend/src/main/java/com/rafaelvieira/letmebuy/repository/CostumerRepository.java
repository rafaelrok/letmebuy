package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rafae
 */
@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long>{

//    @Transactional(readOnly=true)
//    Costumer findByName(String firstName);
}
