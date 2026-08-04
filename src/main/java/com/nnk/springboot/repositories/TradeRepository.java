package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for Trade entity.
 * 
 * Provides CRUD operations and database access for Trade entities.
 * Extends JpaRepository to inherit standard Spring Data JPA functionality.
 */
public interface TradeRepository extends JpaRepository<Trade, Integer> {
}
