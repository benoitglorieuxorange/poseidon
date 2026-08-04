package com.nnk.springboot.services;

import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;

import java.util.List;

/**
 * Service interface for CurvePoint operations.
 * 
 * Defines business logic operations for managing curve points including
 * creation, retrieval, update, and deletion.
 */
public interface CurvePointService {


    List<CurvePointResponseDto> findAllCurvePoint();
    CurvePointResponseDto findByIdCurvepoint(Long id);
    CurvePointResponseDto createCurvePoint(CurvePointRequestDto curvePointRequestDto);
    CurvePointResponseDto updateCurvePoint(Long id, CurvePointRequestDto curvePointRequestDto);
    void deleteCurvePoint(Long id);
}
