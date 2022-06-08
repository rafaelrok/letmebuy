package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional(readOnly=true)
    User findByEmail(String email);
}
