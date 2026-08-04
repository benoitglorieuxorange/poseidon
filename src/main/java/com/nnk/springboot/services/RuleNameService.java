package com.nnk.springboot.services;

import com.nnk.springboot.dtos.RuleNameRequestDto;
import com.nnk.springboot.dtos.RuleNameResponseDto;

import java.util.List;

/**
 * Service interface for RuleName operations.
 *
 * Defines business logic operations for managing rule names including
 * creation, retrieval, update, and deletion.
 */
public interface RuleNameService {

    List<RuleNameResponseDto> findAllRuleName();
    RuleNameResponseDto findByIdRuleName(Long id);
    RuleNameResponseDto createRuleName(RuleNameRequestDto ruleNameRequestDto);
    RuleNameResponseDto updateRuleName(Long id, RuleNameRequestDto ruleNameRequestDto);
    void deleteRuleName(Long id);
}
