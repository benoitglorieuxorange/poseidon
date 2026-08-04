package com.nnk.springboot.dtos;

/**
 * DTO for creating or updating a CurvePoint.
 * 
 * Contains the essential fields required for a curve point request.
 *
 * @param curveId the yield curve identifier
 * @param term the term value
 * @param value the curve value
 */
public record CurvePointRequestDto(
        Integer curveId,
        Double term,
        Double value
) {}
