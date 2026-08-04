package com.nnk.springboot.dtos;

/**
 * DTO for CurvePoint response.
 * 
 * Contains the fields of a curve point entity returned in API responses.
 *
 * @param id the unique identifier
 * @param curveId the yield curve identifier
 * @param term the term value
 * @param value the curve value
 */
public record CurvePointResponseDto(
        Long id,
        Integer curveId,
        Double term,
        Double value
) {}
