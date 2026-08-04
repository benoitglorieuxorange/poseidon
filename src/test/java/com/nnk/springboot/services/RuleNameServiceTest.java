package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dtos.RuleNameRequestDto;
import com.nnk.springboot.dtos.RuleNameResponseDto;
import com.nnk.springboot.mappers.RuleNameMapper;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @Mock
    private RuleNameMapper ruleNameMapper;

    @InjectMocks
    private RuleNameServiceImpl ruleNameService;

    @Test
    void findAllRuleName_returnsMappedDtos() {
        RuleName first = ruleName(1L, "name-1");
        RuleName second = ruleName(2L, "name-2");
        RuleNameResponseDto firstDto = ruleNameDto(1L, "name-1");
        RuleNameResponseDto secondDto = ruleNameDto(2L, "name-2");

        when(ruleNameRepository.findAll()).thenReturn(List.of(first, second));
        when(ruleNameMapper.toResponseDto(first)).thenReturn(firstDto);
        when(ruleNameMapper.toResponseDto(second)).thenReturn(secondDto);

        assertThat(ruleNameService.findAllRuleName()).containsExactly(firstDto, secondDto);
    }

    @Test
    void findByIdRuleName_returnsMappedDto() {
        RuleName entity = ruleName(3L, "name");
        RuleNameResponseDto dto = ruleNameDto(3L, "name");

        when(ruleNameRepository.findById(3L)).thenReturn(Optional.of(entity));
        when(ruleNameMapper.toResponseDto(entity)).thenReturn(dto);

        assertThat(ruleNameService.findByIdRuleName(3L)).isEqualTo(dto);
    }

    @Test
    void findByIdRuleName_throwsWhenMissing() {
        when(ruleNameRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ruleNameService.findByIdRuleName(7L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("RuleName not found with id: 7");
    }

    @Test
    void createRuleName_savesMappedEntity() {
        RuleNameRequestDto request = ruleNameRequest("name");
        RuleName entity = ruleName(null, "name");
        RuleName saved = ruleName(10L, "name");
        RuleNameResponseDto dto = ruleNameDto(10L, "name");

        when(ruleNameMapper.toEntity(request)).thenReturn(entity);
        when(ruleNameRepository.save(entity)).thenReturn(saved);
        when(ruleNameMapper.toResponseDto(saved)).thenReturn(dto);

        assertThat(ruleNameService.createRuleName(request)).isEqualTo(dto);
    }

    @Test
    void updateRuleName_updatesEntity() {
        RuleName existing = ruleName(5L, "old");
        RuleNameRequestDto request = ruleNameRequest("updated");
        RuleNameResponseDto dto = ruleNameDto(5L, "updated");

        when(ruleNameRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(ruleNameRepository.save(existing)).thenReturn(existing);
        when(ruleNameMapper.toResponseDto(existing)).thenReturn(dto);

        assertThat(ruleNameService.updateRuleName(5L, request)).isEqualTo(dto);
        verify(ruleNameMapper).updateFromDto(request, existing);
    }

    @Test
    void updateRuleName_throwsWhenMissing() {
        when(ruleNameRepository.findById(8L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ruleNameService.updateRuleName(8L, ruleNameRequest("x")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("RuleName not found with id: 8");
    }

    @Test
    void deleteRuleName_deletesEntity() {
        RuleName existing = ruleName(6L, "name");
        when(ruleNameRepository.findById(6L)).thenReturn(Optional.of(existing));

        ruleNameService.deleteRuleName(6L);

        verify(ruleNameRepository).delete(existing);
    }

    @Test
    void deleteRuleName_throwsWhenMissing() {
        when(ruleNameRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ruleNameService.deleteRuleName(9L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("RuleName not found with id: 9");
    }

    private static RuleName ruleName(Long id, String name) {
        return new RuleName(id, name, "description", "json", "template", "sqlStr", "sqlPart");
    }

    private static RuleNameRequestDto ruleNameRequest(String name) {
        return new RuleNameRequestDto(name, "description", "json", "template", "sqlStr", "sqlPart");
    }

    private static RuleNameResponseDto ruleNameDto(Long id, String name) {
        return new RuleNameResponseDto(id, name, "description", "json", "template", "sqlStr", "sqlPart");
    }
}
