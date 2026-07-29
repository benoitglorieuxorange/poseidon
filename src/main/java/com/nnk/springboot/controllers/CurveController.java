package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.mappers.CurvePointMapper;
import com.nnk.springboot.services.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class CurveController {

    // TODO: Inject Curve Point service
    private final CurvePointService curvePointService;
    private final CurvePointMapper curvePointMapper;

    public CurveController(CurvePointService curvePointService, CurvePointMapper curvePointMapper) {
        this.curvePointService = curvePointService;
        this.curvePointMapper = curvePointMapper;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        // TODO: find all Curve Point, add to model
        model.addAttribute("curvePoints", curvePointService.findAllCurvePoint());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(@ModelAttribute ("curvePoint") CurvePointRequestDto curvePoint) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointRequestDto curvePoint,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.createCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        // TODO: get CurvePoint by Id and to model then show to the form
        var existing = curvePointService.findByIdCurvepoint(id);
        model.addAttribute("curvePoint", curvePointMapper.toRequestDto(existing));
        model.addAttribute("curvePointId", id);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid @ModelAttribute("curvePoint") CurvePointRequestDto curvePoint,
                             BindingResult result, Model model) {
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(id, curvePoint);
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {
        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
