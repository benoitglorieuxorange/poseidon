package com.nnk.springboot.dtos;

public record CurvePointResponseDto(
        Long id,
        Integer curveId,
        Double term,
        Double value
) {}
