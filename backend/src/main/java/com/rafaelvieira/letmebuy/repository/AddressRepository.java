package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author rafae
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByZipcode(String zipcode);

}
