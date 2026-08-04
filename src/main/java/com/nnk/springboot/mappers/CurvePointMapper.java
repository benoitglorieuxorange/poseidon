package com.nnk.springboot.mappers;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between CurvePoint entity and DTOs.
 * 
 * Handles conversion between CurvePoint JPA entities and their corresponding
 * request/response DTOs for API layer operations.
 */
@Component
public class CurvePointMapper {

    /**
     * Converts a CurvePoint entity to a CurvePointResponseDto.
     *
     * @param curvePoint the CurvePoint entity to be converted
     * @return a CurvePointResponseDto containing the same values as the provided entity, or null if input is null
     */
    public CurvePointResponseDto toResponseDto(CurvePoint curvePoint) {
        if (curvePoint == null) {
            return null;
        }
        return new CurvePointResponseDto(
                curvePoint.getId(),
                curvePoint.getCurveId(),
                curvePoint.getTerm(),
                curvePoint.getValue()
        );
    }

    /**
     * Converts a CurvePointRequestDto to a CurvePoint entity.
     *
     * @param dto the CurvePointRequestDto to be converted
     * @return a new CurvePoint entity containing the same values as the provided DTO, or null if input is null
     */
    public CurvePoint toEntity(CurvePointRequestDto dto) {
        if (dto == null) {
            return null;
        }
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(dto.curveId());
        curvePoint.setTerm(dto.term());
        curvePoint.setValue(dto.value());
        return curvePoint;
    }

    /**
     * Updates an existing CurvePoint entity with values from a CurvePointRequestDto.
     *
     * @param dto the CurvePointRequestDto containing the new values
     * @param curvePoint the existing CurvePoint entity to be updated
     */
    public void updateFromDto (CurvePointRequestDto dto, CurvePoint curvePoint) {
        if (dto == null || curvePoint == null) {
            return;
        }
        curvePoint.setCurveId(dto.curveId());
        curvePoint.setTerm(dto.term());
        curvePoint.setValue(dto.value());
    }

    /**
     * Converts a CurvePointResponseDto to a CurvePointRequestDto.
     *
     * @param responseDto the CurvePointResponseDto to be converted
     * @return a new CurvePointRequestDto containing the same values as the provided DTO, or null if input is null
     */
    public CurvePointRequestDto toRequestDto(CurvePointResponseDto responseDto) {
        if (responseDto == null) {
            return null;
        }
        return new CurvePointRequestDto(
                responseDto.curveId(),
                responseDto.term(),
                responseDto.value()
        );
    }


}
