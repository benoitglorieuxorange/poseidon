package com.nnk.springboot.mappers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dtos.RuleNameRequestDto;
import com.nnk.springboot.dtos.RuleNameResponseDto;
import org.springframework.stereotype.Component;

@Component
public class RuleNameMapper {

    public RuleNameResponseDto toResponseDto(RuleName ruleName) {
        if (ruleName == null) {
            return null;
        }
        return new RuleNameResponseDto(
                ruleName.getId(),
                ruleName.getName(),
                ruleName.getDescription(),
                ruleName.getJson(),
                ruleName.getTemplate(),
                ruleName.getSqlStr(),
                ruleName.getSqlPart()
        );
    }

    public RuleName toEntity(RuleNameRequestDto ruleNameRequestDto) {
        if (ruleNameRequestDto == null) {
            return null;
        }
        RuleName ruleName = new RuleName();
        ruleName.setName(ruleNameRequestDto.name());
        ruleName.setDescription(ruleNameRequestDto.description());
        ruleName.setJson(ruleNameRequestDto.json());
        ruleName.setTemplate(ruleNameRequestDto.template());
        ruleName.setSqlStr(ruleNameRequestDto.sqlStr());
        ruleName.setSqlPart(ruleNameRequestDto.sqlPart());
        return ruleName;
    }

    public void updateFromDto(RuleNameRequestDto ruleNameRequestDto, RuleName ruleName) {
        if (ruleNameRequestDto == null || ruleName == null) {
            return;
        }
        ruleName.setName(ruleNameRequestDto.name());
        ruleName.setDescription(ruleNameRequestDto.description());
        ruleName.setJson(ruleNameRequestDto.json());
        ruleName.setTemplate(ruleNameRequestDto.template());
        ruleName.setSqlStr(ruleNameRequestDto.sqlStr());
        ruleName.setSqlPart(ruleNameRequestDto.sqlPart());
    }

    public RuleNameRequestDto toRequestDto(RuleNameResponseDto ruleNameResponseDto) {
        if (ruleNameResponseDto == null) {
            return null;
        }
        return new RuleNameRequestDto(
                ruleNameResponseDto.name(),
                ruleNameResponseDto.description(),
                ruleNameResponseDto.json(),
                ruleNameResponseDto.template(),
                ruleNameResponseDto.sqlStr(),
                ruleNameResponseDto.sqlPart()
        );
    }

}
