package com.nnk.springboot.controllers;



import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import com.nnk.springboot.mappers.BidListMapper;
import com.nnk.springboot.services.BidListService;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * REST Controller for managing BidList entities.
 * 
 * Provides endpoints for CRUD operations on bid lists including
 * listing, creating, updating, and deleting bid entries.
 */
@Controller
public class BidListController {

    private final BidListService bidListService;
    private final BidListMapper bidListMapper;

    /**
     * Constructs a BidListController with required dependencies.
     *
     * @param bidListService the bid list service
     * @param bidListMapper the bid list mapper
     */
    public BidListController(BidListService bidListService, BidListMapper bidListMapper) {
        this.bidListService = bidListService;
        this.bidListMapper = bidListMapper;
    }

    /**
     * Displays the list of all bid lists.
     *
     * @param model the model to add attributes to
     * @return the view name for bid list listing page
     */
    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        model.addAttribute("bidLists", bidListService.findAllBidList());
        return "bidList/list";
    }

    /**
     * Displays the form to add a new bid list.
     *
     * @param bidList the bid list request DTO
     * @return the view name for bid list add page
     */
    @GetMapping("/bidList/add")
    public String addBidForm(@ModelAttribute("bidList") BidListRequestDto bidList) {
        return "bidList/add";
    }

    /**
     * Validates and creates a new bid list.
     *
     * @param bidList the bid list response DTO
     * @param result the binding result for validation errors
     * @param model the model
     * @return redirect to bid list page on success, or the add page on validation error
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListResponseDto bidList,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.createBidList(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Displays the form to update an existing bid list.
     *
     * @param id the bid list ID
     * @param model the model
     * @return the view name for bid list update page
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        var existing = bidListService.findByIdBidList(id);
        model.addAttribute("bidList", bidListMapper.toRequestDto(existing));
        model.addAttribute("bidListId", id);
        return "bidList/update";
    }

    /**
     * Validates and updates an existing bid list.
     *
     * @param id the bid list ID
     * @param bidList the updated bid list request DTO
     * @param result the binding result for validation errors
     * @param model the model
     * @return redirect to bid list page on success, or the update page on validation error
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Long id, @Valid @ModelAttribute("bidList") BidListRequestDto bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bidListId", id);
            return "bidList/update";
        }
        bidListService.updateBidList(id, bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Deletes a bid list by its ID.
     *
     * @param id the bid list ID to delete
     * @param model the model
     * @return redirect to bid list page
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
