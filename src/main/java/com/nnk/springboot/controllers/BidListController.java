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


@Controller
public class BidListController {

    private final BidListService bidListService;
    private final BidListMapper bidListMapper;

    public BidListController(BidListService bidListService, BidListMapper bidListMapper) {
        this.bidListService = bidListService;
        this.bidListMapper = bidListMapper;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        // TODO: call service find all bids to show to the view
        model.addAttribute("bidLists", bidListService.findAllBidList());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(@ModelAttribute("bidList") BidListRequestDto bidList) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidListResponseDto bidList,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.createBidList(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        var existing = bidListService.findByIdBidList(id);
        model.addAttribute("bidList", bidListMapper.toRequestDto(existing));
        model.addAttribute("bidListId", id);
        return "bidList/update";
    }

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

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Long id, Model model) {
        // TODO: Find Bid by Id and delete the bid, return to Bid list
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
