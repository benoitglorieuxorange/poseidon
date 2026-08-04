package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.TradeRequestDto;
import com.nnk.springboot.dtos.TradeResponseDto;
import com.nnk.springboot.mappers.TradeMapper;
import com.nnk.springboot.services.TradeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Trade entities.
 * 
 * Provides endpoints for CRUD operations on trades including
 * listing, creating, updating, and deleting trade entries.
 * Note: Implementation is incomplete with TODO comments for required functionality.
 */
@Controller
public class TradeController {

    private final TradeService tradeService;
    private final TradeMapper tradeMapper;

    public TradeController(TradeService tradeService, TradeMapper tradeMapper) {
        this.tradeService = tradeService;
        this.tradeMapper = tradeMapper;
    }

    /**
     * Displays the list of all trades.
     *
     * @param model the model to add attributes to
     * @return the view name for trade listing page
     */
    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.findAllTrades());
        return "trade/list";
    }

    /**
     * Displays the form to add a new trade.
     *
     * @param bid the trade entity
     * @return the view name for trade add page
     */
    @GetMapping("/trade/add")
    public String addUser(@ModelAttribute("trade") TradeRequestDto trade) {
        return "trade/add";
    }

    /**
     * Validates and creates a new trade.
     *
     * @param trade  the trade entity
     * @param result the binding result for validation errors
     * @param model  the model
     * @return the add page view
     */
    @PostMapping("/trade/validate")
    public String validate(@Valid @ModelAttribute("trade") TradeRequestDto trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.createTrade(trade);
        return "redirect:/trade/list";
    }

    /**
     * Displays the form to update an existing trade.
     *
     * @param id    the trade ID
     * @param model the model
     * @return the view name for trade update page
     */
    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        TradeResponseDto existing = tradeService.findByIdTrade(id);
        model.addAttribute("trade", tradeMapper.toRequestDto(existing));
        model.addAttribute("tradeId", id);
        return "trade/update";
    }

    /**
     * Validates and updates an existing trade.
     *
     * @param id     the trade ID
     * @param trade  the updated trade entity
     * @param result the binding result for validation errors
     * @param model  the model
     * @return redirect to trade page
     */
    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") TradeRequestDto trade,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("tradeId", id);
            return "trade/update";
        }
        tradeService.updateTrade(id, trade);
        return "redirect:/trade/list";
    }

    /**
     * Deletes a trade by its ID.
     *
     * @param id    the trade ID to delete
     * @param model the model
     * @return redirect to trade page
     */
    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTrade(id);
        return "redirect:/trade/list";
    }
}
