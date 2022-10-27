package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rafae
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
