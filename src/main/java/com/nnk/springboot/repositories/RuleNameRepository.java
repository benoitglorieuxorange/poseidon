package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for RuleName entity.
 * 
 * Provides CRUD operations and database access for RuleName entities.
 * Extends JpaRepository to inherit standard Spring Data JPA functionality.
 */
public interface RuleNameRepository extends JpaRepository<RuleName, Long> {
}
