package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;
import com.nnk.springboot.mappers.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for CurvePoint operations.
 * 
 * Provides CRUD operations for curve points using the repository and mapper patterns.
 */
@Service
public class CurvePointServiceImpl implements CurvePointService {

    private final CurvePointRepository curvePointRepository;
    private final CurvePointMapper curvePointMapper;

    /**
     * Constructs a CurvePointServiceImpl with required dependencies.
     *
     * @param curvePointRepository the curve point repository
     * @param curvePointMapper the curve point mapper
     */
    public CurvePointServiceImpl(CurvePointRepository curvePointRepository, CurvePointMapper curvePointMapper) {
        this.curvePointRepository = curvePointRepository;
        this.curvePointMapper = curvePointMapper;
    }


    /**
     * Retrieves all curve points.
     *
     * @return a list of all curve points
     */
    @Override
    public List<CurvePointResponseDto> findAllCurvePoint() {
        return curvePointRepository.findAll()
                .stream()
                .map(curvePointMapper::toResponseDto)
                .toList();
    }

    /**
     * Retrieves a specific curve point by its ID.
     *
     * @param id the curve point ID
     * @return the curve point with the specified ID
     * @throws RuntimeException if the curve point is not found
     */
    @Override
    public CurvePointResponseDto findByIdCurvepoint(Long id) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new RuntimeException("CurvePoint not found with id: " + id));
        return curvePointMapper.toResponseDto(curvePoint);
    }

    /**
     * Creates a new curve point.
     *
     * @param curvePointRequestDto the curve point data to create
     * @return the created curve point
     */
    @Override
    public CurvePointResponseDto createCurvePoint(CurvePointRequestDto curvePointRequestDto) {
        CurvePoint curvePoint = new CurvePoint();
        curvePointMapper.updateFromDto(curvePointRequestDto, curvePoint);
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        return curvePointMapper.toResponseDto(savedCurvePoint);
    }

    /**
     * Updates an existing curve point.
     *
     * @param id the curve point ID to update
     * @param curvePointRequestDto the updated curve point data
     * @return the updated curve point
     * @throws RuntimeException if the curve point is not found
     */
    @Override
    public CurvePointResponseDto updateCurvePoint(Long id, CurvePointRequestDto curvePointRequestDto) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new RuntimeException("CurvePoint not found with id: " + id));
        curvePointMapper.updateFromDto(curvePointRequestDto, curvePoint);
        CurvePoint updatedCurvePoint = curvePointRepository.save(curvePoint);
        return curvePointMapper.toResponseDto(updatedCurvePoint);
    }

    /**
     * Deletes a curve point by its ID.
     *
     * @param id the curve point ID to delete
     * @throws RuntimeException if the curve point is not found
     */
    @Override
    public void deleteCurvePoint(Long id) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new RuntimeException("CurvePoint not found with id: " + id));
        curvePointRepository.delete(curvePoint);
    }
}
