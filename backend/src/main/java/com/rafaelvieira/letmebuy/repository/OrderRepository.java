package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Costumer;
import com.rafaelvieira.letmebuy.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rafae
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Transactional(readOnly=true)
    Page<Order> findByCostumer(Costumer costumer, Pageable pageRequest);
}


