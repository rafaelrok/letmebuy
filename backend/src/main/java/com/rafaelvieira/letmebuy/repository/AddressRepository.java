package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
