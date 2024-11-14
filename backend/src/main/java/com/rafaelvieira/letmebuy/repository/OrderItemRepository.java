package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.OrderItem;
import com.rafaelvieira.letmebuy.entities.OrderItemPK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rafae
 */

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
