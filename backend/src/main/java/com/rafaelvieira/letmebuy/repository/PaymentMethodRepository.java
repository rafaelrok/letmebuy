package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {


}

