package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Rating entity.
 * 
 * Provides CRUD operations and database access for Rating entities.
 * Extends JpaRepository to inherit standard Spring Data JPA functionality.
 */
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
