package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;
import com.nnk.springboot.mappers.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    private final CurvePointRepository curvePointRepository;
    private final CurvePointMapper curvePointMapper;

    public CurvePointServiceImpl(CurvePointRepository curvePointRepository, CurvePointMapper curvePointMapper) {
        this.curvePointRepository = curvePointRepository;
        this.curvePointMapper = curvePointMapper;
    }


    @Override
    public List<CurvePointResponseDto> findAllCurvePoint() {
        return curvePointRepository.findAll()
                .stream()
                .map(curvePointMapper::toResponseDto)
                .toList();
    }

    @Override
    public CurvePointResponseDto findByIdCurvepoint(Long id) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new RuntimeException("CurvePoint not found with id: " + id));
        return curvePointMapper.toResponseDto(curvePoint);
    }

    @Override
    public CurvePointResponseDto createCurvePoint(CurvePointRequestDto curvePointRequestDto) {
        CurvePoint curvePoint = new CurvePoint();
        curvePointMapper.updateFromDto(curvePointRequestDto, curvePoint);
        CurvePoint savedCurvePoint = curvePointRepository.save(curvePoint);
        return curvePointMapper.toResponseDto(savedCurvePoint);
    }

    @Override
    public CurvePointResponseDto updateCurvePoint(Long id, CurvePointRequestDto curvePointRequestDto) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new RuntimeException("CurvePoint not found with id: " + id));
        curvePointMapper.updateFromDto(curvePointRequestDto, curvePoint);
        CurvePoint updatedCurvePoint = curvePointRepository.save(curvePoint);
        return curvePointMapper.toResponseDto(updatedCurvePoint);
    }

    @Override
    public void deleteCurvePoint(Long id) {
        CurvePoint curvePoint = curvePointRepository.findById(id).orElseThrow(() -> new RuntimeException("CurvePoint not found with id: " + id));
        curvePointRepository.delete(curvePoint);
    }
}
