package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
