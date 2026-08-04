package com.nnk.springboot.dtos;

public record RuleNameResponseDto(
        Long id,
        String name,
        String description,
        String json,
        String template,
        String sqlStr,
        String sqlPart
) {}
