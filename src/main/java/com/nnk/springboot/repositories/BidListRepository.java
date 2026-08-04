package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for BidList entity.
 * 
 * Provides CRUD operations and database access for BidList entities.
 * Extends JpaRepository to inherit standard Spring Data JPA functionality.
 */
public interface BidListRepository extends JpaRepository<BidList, Long> {

}
