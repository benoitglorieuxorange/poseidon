package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.mappers.CurvePointMapper;
import com.nnk.springboot.services.CurvePointService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing CurvePoint entities.
 * 
 * Provides endpoints for CRUD operations on curve points including
 * listing, creating, updating, and deleting curve point entries.
 */
@Controller
public class CurveController {

    private final CurvePointService curvePointService;
    private final CurvePointMapper curvePointMapper;

    /**
     * Constructs a CurveController with required dependencies.
     *
     * @param curvePointService the curve point service
     * @param curvePointMapper  the curve point mapper
     */
    public CurveController(CurvePointService curvePointService, CurvePointMapper curvePointMapper) {
        this.curvePointService = curvePointService;
        this.curvePointMapper = curvePointMapper;
    }

    /**
     * Displays the list of all curve points.
     *
     * @param model the model to add attributes to
     * @return the view name for curve point listing page
     */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.findAllCurvePoint());
        return "curvePoint/list";
    }

    /**
     * Displays the form to add a new curve point.
     *
     * @param curvePoint the curve point request DTO
     * @return the view name for curve point add page
     */
    @GetMapping("/curvePoint/add")
    public String addBidForm(@ModelAttribute("curvePoint") CurvePointRequestDto curvePoint) {
        return "curvePoint/add";
    }

    /**
     * Validates and creates a new curve point.
     *
     * @param curvePoint the curve point request DTO
     * @param result     the binding result for validation errors
     * @param model      the model
     * @return redirect to curve point page on success, or the add page on validation error
     */
    @PostMapping("/curvePoint/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePointRequestDto curvePoint,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.createCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Displays the form to update an existing curve point.
     *
     * @param id    the curve point ID
     * @param model the model
     * @return the view name for curve point update page
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        var existing = curvePointService.findByIdCurvepoint(id);
        model.addAttribute("curvePoint", curvePointMapper.toRequestDto(existing));
        model.addAttribute("curvePointId", id);
        return "curvePoint/update";
    }

    /**
     * Validates and updates an existing curve point.
     *
     * @param id         the curve point ID
     * @param curvePoint the updated curve point request DTO
     * @param result     the binding result for validation errors
     * @param model      the model
     * @return redirect to curve point page on success, or the update page on validation error
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid @ModelAttribute("curvePoint") CurvePointRequestDto curvePoint,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePointService.updateCurvePoint(id, curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Deletes a curve point by its ID.
     *
     * @param id    the curve point ID to delete
     * @param model the model
     * @return redirect to curve point page
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {
        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}

