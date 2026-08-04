package com.nnk.springboot.dtos;

public record RuleNameRequestDto(
        String name,
        String description,
        String json,
        String template,
        String sqlStr,
        String sqlPart
) {}
