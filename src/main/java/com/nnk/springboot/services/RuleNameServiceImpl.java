package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dtos.RuleNameRequestDto;
import com.nnk.springboot.dtos.RuleNameResponseDto;
import com.nnk.springboot.mappers.RuleNameMapper;
import com.nnk.springboot.repositories.RuleNameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the RuleNameService interface.
 * Provides business logic for managing rule name operations including
 * creating, reading, updating, and deleting rule name entries.
 */
@Service
public class RuleNameServiceImpl implements RuleNameService {

    private final RuleNameRepository ruleNameRepository;
    private final RuleNameMapper ruleNameMapper;

    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository, RuleNameMapper ruleNameMapper) {
        this.ruleNameRepository = ruleNameRepository;
        this.ruleNameMapper = ruleNameMapper;
    }

    /**
     * Retrieves all rule names from the database.
     *
     * @return a list of RuleNameResponseDto containing all rule names
     */
    @Override
    public List<RuleNameResponseDto> findAllRuleName() {
        return ruleNameRepository.findAll()
                .stream()
                .map(ruleNameMapper::toResponseDto)
                .toList();
    }

    /**
     * Retrieves a specific rule name by its ID.
     *
     * @param id the rule name ID
     * @return the RuleNameResponseDto of the found rule name
     * @throws RuntimeException if no rule name is found with the given ID
     */
    @Override
    public RuleNameResponseDto findByIdRuleName(Long id) {
        return ruleNameRepository.findById(id)
                .map(ruleNameMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("RuleName not found with id: " + id));
    }

    /**
     * Creates a new rule name.
     *
     * @param ruleNameRequestDto the rule name data to create
     * @return the created RuleNameResponseDto
     */
    @Override
    @Transactional
    public RuleNameResponseDto createRuleName(RuleNameRequestDto ruleNameRequestDto) {
        RuleName ruleName = ruleNameMapper.toEntity(ruleNameRequestDto);
        RuleName savedRuleName = ruleNameRepository.save(ruleName);
        return ruleNameMapper.toResponseDto(savedRuleName);
    }

    /**
     * Updates an existing rule name.
     *
     * @param id the rule name ID to update
     * @param ruleNameRequestDto the updated rule name data
     * @return the updated RuleNameResponseDto
     * @throws RuntimeException if no rule name is found with the given ID
     */
    @Override
    @Transactional
    public RuleNameResponseDto updateRuleName(Long id, RuleNameRequestDto ruleNameRequestDto) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RuleName not found with id: " + id));
        ruleNameMapper.updateFromDto(ruleNameRequestDto, ruleName);
        RuleName updatedRuleName = ruleNameRepository.save(ruleName);
        return ruleNameMapper.toResponseDto(updatedRuleName);
    }

    /**
     * Deletes a rule name by its ID.
     *
     * @param id the rule name ID to delete
     * @throws RuntimeException if no rule name is found with the given ID
     */
    @Override
    public void deleteRuleName(Long id) {
        RuleName ruleName = ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RuleName not found with id: " + id));
        ruleNameRepository.delete(ruleName);
    }
}
