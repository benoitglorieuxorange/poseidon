package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dtos.RatingRequestDto;
import com.nnk.springboot.dtos.RatingResponseDto;
import com.nnk.springboot.mappers.RatingMapper;
import com.nnk.springboot.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * REST Controller for managing Rating entities.
 * 
 * Provides endpoints for CRUD operations on ratings including
 * listing, creating, updating, and deleting rating entries.
 * Note: Implementation is incomplete with TODO comments for required functionality.
 */

@Controller
public class RatingController {

    private final RatingService ratingService;
    private final RatingMapper ratingMapper;

    public RatingController(RatingService ratingService, RatingMapper ratingMapper) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
    }

    /**
     * Displays the list of all ratings.
     *
     * @param model the model to add attributes to
     * @return the view name for rating listing page
     */
    @RequestMapping("/rating/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.findAllRatings());
        return "rating/list";
    }

    /**
     * Displays the form to add a new rating.
     *
     * @param rating the rating entity
     * @return the view name for rating add page
     */
    @GetMapping("/rating/add")
    public String addRatingForm(@ModelAttribute("rating") RatingRequestDto rating) { return "rating/add"; }

    /**
     * Validates and creates a new rating.
     *
     * @param rating the rating entity
     * @param result the binding result for validation errors
     * @param model  the model
     * @return the add page view
     */
    @PostMapping("/rating/validate")
    public String validate(@Valid @ModelAttribute("rating") RatingRequestDto rating, BindingResult result, Model model) {

        if (result.hasErrors()){
            return "rating/add";
        }
        ratingService.createRating(rating);
        return "redirect:/rating/list";
    }

    /**
     * Displays the form to update an existing rating.
     *
     * @param id    the rating ID
     * @param model the model
     * @return the view name for rating update page
     */
    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        RatingResponseDto existing = ratingService.findByIdRating(id);
        model.addAttribute("rating", ratingMapper.toRequestDto(existing));
        model.addAttribute("ratingId", id);
        return "rating/update";
    }

    /**
     * Validates and updates an existing rating.
     *
     * @param id     the rating ID
     * @param rating the updated rating entity
     * @param result the binding result for validation errors
     * @param model  the model
     * @return redirect to rating page
     */
    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Long id, @Valid @ModelAttribute("rating") RatingRequestDto rating,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("ratingId", id);
            return "rating/update";
        }
        ratingService.updateRating(id, rating);
        return "redirect:/rating/list";
    }

    /**
     * Deletes a rating by its ID.
     *
     * @param id    the rating ID to delete
     * @param model the model
     * @return redirect to rating page
     */
    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Long id, Model model) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}