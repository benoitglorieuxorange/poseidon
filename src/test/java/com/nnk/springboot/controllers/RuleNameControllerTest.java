package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.RuleNameRequestDto;
import com.nnk.springboot.dtos.RuleNameResponseDto;
import com.nnk.springboot.mappers.RuleNameMapper;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleNameControllerTest {

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private RuleNameMapper ruleNameMapper;

    @InjectMocks
    private RuleNameController ruleNameController;

    @Test
    void home_addsRuleNamesAndReturnsListView() {
        Model model = new ExtendedModelMap();
        List<RuleNameResponseDto> ruleNames = List.of(new RuleNameResponseDto(1L, "name", "description", "json", "template", "sqlStr", "sqlPart"));
        when(ruleNameService.findAllRuleName()).thenReturn(ruleNames);

        assertThat(ruleNameController.home(model)).isEqualTo("ruleName/list");
        assertThat(model.asMap().get("ruleNames")).isEqualTo(ruleNames);
    }

    @Test
    void addRuleForm_returnsAddView() {
        assertThat(ruleNameController.addRuleForm(new RuleNameRequestDto("name", "description", "json", "template", "sqlStr", "sqlPart")))
                .isEqualTo("ruleName/add");
    }

    @Test
    void validate_returnsAddViewWhenValidationFails() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(ruleNameController.validate(ruleNameRequest(), result, new ExtendedModelMap())).isEqualTo("ruleName/add");
    }

    @Test
    void validate_createsRuleNameAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        RuleNameRequestDto request = ruleNameRequest();
        when(result.hasErrors()).thenReturn(false);

        assertThat(ruleNameController.validate(request, result, new ExtendedModelMap())).isEqualTo("redirect:/ruleName/list");
        verify(ruleNameService).createRuleName(request);
    }

    @Test
    void showUpdateForm_populatesModelAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        RuleNameResponseDto response = new RuleNameResponseDto(2L, "name", "description", "json", "template", "sqlStr", "sqlPart");
        RuleNameRequestDto request = ruleNameRequest();
        when(ruleNameService.findByIdRuleName(2L)).thenReturn(response);
        when(ruleNameMapper.toRequestDto(response)).thenReturn(request);

        assertThat(ruleNameController.showUpdateForm(2L, model)).isEqualTo("ruleName/update");
        assertThat(model.asMap().get("ruleName")).isEqualTo(request);
        assertThat(model.asMap().get("ruleNameId")).isEqualTo(2L);
    }

    @Test
    void updateRuleName_returnsUpdateViewWhenValidationFails() {
        Model model = new ExtendedModelMap();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(ruleNameController.updateRuleName(3L, ruleNameRequest(), result, model)).isEqualTo("ruleName/update");
        assertThat(model.asMap().get("ruleNameId")).isEqualTo(3L);
    }

    @Test
    void updateRuleName_updatesAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        RuleNameRequestDto request = ruleNameRequest();
        when(result.hasErrors()).thenReturn(false);

        assertThat(ruleNameController.updateRuleName(3L, request, result, new ExtendedModelMap()))
                .isEqualTo("redirect:/ruleName/list");
        verify(ruleNameService).updateRuleName(3L, request);
    }

    @Test
    void deleteRuleName_deletesAndRedirects() {
        assertThat(ruleNameController.deleteRuleName(4L, new ExtendedModelMap())).isEqualTo("redirect:/ruleName/list");
        verify(ruleNameService).deleteRuleName(4L);
    }

    private static RuleNameRequestDto ruleNameRequest() {
        return new RuleNameRequestDto("name", "description", "json", "template", "sqlStr", "sqlPart");
    }
}
