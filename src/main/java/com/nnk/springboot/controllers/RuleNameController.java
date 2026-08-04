package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.RuleNameRequestDto;
import com.nnk.springboot.dtos.RuleNameResponseDto;
import com.nnk.springboot.mappers.RuleNameMapper;
import com.nnk.springboot.services.RuleNameService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class RuleNameController {

    private final RuleNameService ruleNameService;
    private final RuleNameMapper ruleNameMapper;

    public RuleNameController(RuleNameService ruleNameService, RuleNameMapper ruleNameMapper) {
        this.ruleNameService = ruleNameService;
        this.ruleNameMapper = ruleNameMapper;
    }

    @RequestMapping("/ruleName/list")
    public String home(Model model)
    {
        model.addAttribute("ruleNames", ruleNameService.findAllRuleName());
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(@ModelAttribute("ruleName") RuleNameRequestDto ruleName) {
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleNameRequestDto ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.createRuleName(ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        RuleNameResponseDto existing = ruleNameService.findByIdRuleName(id);
        model.addAttribute("ruleName", ruleNameMapper.toRequestDto(existing));
        model.addAttribute("ruleNameId", id);
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Long id, @Valid @ModelAttribute("ruleName") RuleNameRequestDto ruleName,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ruleNameId", id);
            return "ruleName/update";
        }
        ruleNameService.updateRuleName(id, ruleName);
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Long id, Model model) {
        ruleNameService.deleteRuleName(id);
        return "redirect:/ruleName/list";
    }
}
