package com.rafaelvieira.letmebuy.repository;

import com.rafaelvieira.letmebuy.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author rafae
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

}

