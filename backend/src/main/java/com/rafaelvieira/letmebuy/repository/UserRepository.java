package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Product;
import com.rafaelvieira.letmebuy.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author rafae
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
