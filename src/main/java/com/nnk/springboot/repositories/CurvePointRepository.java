package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for CurvePoint entity.
 * 
 * Provides CRUD operations and database access for CurvePoint entities.
 * Extends JpaRepository to inherit standard Spring Data JPA functionality.
 */
public interface CurvePointRepository extends JpaRepository<CurvePoint, Long> {

}
