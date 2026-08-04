package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;
import com.nnk.springboot.mappers.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @Mock
    private CurvePointMapper curvePointMapper;

    @InjectMocks
    private CurvePointServiceImpl curvePointService;

    @Test
    void findAllCurvePoint_returnsMappedDtos() {
        CurvePoint first = curvePoint(1L, 10, 1.0, 2.0);
        CurvePoint second = curvePoint(2L, 20, 3.0, 4.0);
        CurvePointResponseDto firstDto = curvePointDto(1L, 10, 1.0, 2.0);
        CurvePointResponseDto secondDto = curvePointDto(2L, 20, 3.0, 4.0);

        when(curvePointRepository.findAll()).thenReturn(List.of(first, second));
        when(curvePointMapper.toResponseDto(first)).thenReturn(firstDto);
        when(curvePointMapper.toResponseDto(second)).thenReturn(secondDto);

        assertThat(curvePointService.findAllCurvePoint()).containsExactly(firstDto, secondDto);
    }

    @Test
    void findByIdCurvepoint_returnsMappedDto() {
        CurvePoint entity = curvePoint(3L, 10, 1.0, 2.0);
        CurvePointResponseDto dto = curvePointDto(3L, 10, 1.0, 2.0);

        when(curvePointRepository.findById(3L)).thenReturn(Optional.of(entity));
        when(curvePointMapper.toResponseDto(entity)).thenReturn(dto);

        assertThat(curvePointService.findByIdCurvepoint(3L)).isEqualTo(dto);
    }

    @Test
    void findByIdCurvepoint_throwsWhenMissing() {
        when(curvePointRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> curvePointService.findByIdCurvepoint(7L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("CurvePoint not found with id: 7");
    }

    @Test
    void createCurvePoint_updatesFreshEntityBeforeSaving() {
        CurvePointRequestDto request = new CurvePointRequestDto(10, 1.5, 2.5);
        CurvePoint saved = curvePoint(8L, 10, 1.5, 2.5);
        CurvePointResponseDto dto = curvePointDto(8L, 10, 1.5, 2.5);

        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(saved);
        when(curvePointMapper.toResponseDto(saved)).thenReturn(dto);

        assertThat(curvePointService.createCurvePoint(request)).isEqualTo(dto);
        verify(curvePointMapper).updateFromDto(eq(request), any(CurvePoint.class));
    }

    @Test
    void updateCurvePoint_updatesEntity() {
        CurvePoint existing = curvePoint(5L, 1, 1.0, 2.0);
        CurvePointRequestDto request = new CurvePointRequestDto(2, 3.0, 4.0);
        CurvePointResponseDto dto = curvePointDto(5L, 2, 3.0, 4.0);

        when(curvePointRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(curvePointRepository.save(existing)).thenReturn(existing);
        when(curvePointMapper.toResponseDto(existing)).thenReturn(dto);

        assertThat(curvePointService.updateCurvePoint(5L, request)).isEqualTo(dto);
        verify(curvePointMapper).updateFromDto(request, existing);
    }

    @Test
    void updateCurvePoint_throwsWhenMissing() {
        when(curvePointRepository.findById(8L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> curvePointService.updateCurvePoint(8L, new CurvePointRequestDto(1, 1.0, 2.0)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("CurvePoint not found with id: 8");
    }

    @Test
    void deleteCurvePoint_deletesEntity() {
        CurvePoint existing = curvePoint(6L, 10, 1.0, 2.0);
        when(curvePointRepository.findById(6L)).thenReturn(Optional.of(existing));

        curvePointService.deleteCurvePoint(6L);

        verify(curvePointRepository).delete(existing);
    }

    @Test
    void deleteCurvePoint_throwsWhenMissing() {
        when(curvePointRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> curvePointService.deleteCurvePoint(9L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("CurvePoint not found with id: 9");
    }

    private static CurvePoint curvePoint(Long id, Integer curveId, Double term, Double value) {
        return new CurvePoint(id, curveId, null, term, value, null);
    }

    private static CurvePointResponseDto curvePointDto(Long id, Integer curveId, Double term, Double value) {
        return new CurvePointResponseDto(id, curveId, term, value);
    }
}
