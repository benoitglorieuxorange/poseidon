package com.nnk.springboot.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

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
        @NotNull(message = "CurveId is mandatory")
        @Digits(integer = 10, fraction = 0, message = "CurveId must contain only numbers")
        Integer curveId,
        @NotNull(message = "Term is mandatory")
        @Digits(integer = 10, fraction = 3, message = "Term must contain only numbers")
        Double term,
        @NotNull(message = "Value is mandatory")
        @Digits(integer = 10, fraction = 3, message = "Value must contain only numbers")
        Double value
) {}
