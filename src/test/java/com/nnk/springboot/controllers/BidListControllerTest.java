package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import com.nnk.springboot.mappers.BidListMapper;
import com.nnk.springboot.services.BidListService;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BidListControllerTest {

    @Mock
    private BidListService bidListService;

    @Mock
    private BidListMapper bidListMapper;

    @InjectMocks
    private BidListController bidListController;

    @Test
    void home_addsBidListsAndReturnsListView() {
        Model model = new ExtendedModelMap();
        List<BidListResponseDto> bidLists = List.of(new BidListResponseDto(1L, "account", "type", 10.0));
        when(bidListService.findAllBidList()).thenReturn(bidLists);

        String viewName = bidListController.home(model);

        assertThat(viewName).isEqualTo("bidList/list");
        assertThat(model.asMap().get("bidLists")).isEqualTo(bidLists);
    }

    @Test
    void addBidForm_returnsAddView() {
        String viewName = bidListController.addBidForm(new BidListRequestDto("account", "type", 10.0));

        assertThat(viewName).isEqualTo("bidList/add");
    }

    @Test
    void validate_returnsAddView_whenValidationFails() {
        BindingResult result = org.mockito.Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String viewName = bidListController.validate(
                new BidListResponseDto(null, "account", "type", 10.0),
                result,
                new ExtendedModelMap()
        );

        assertThat(viewName).isEqualTo("bidList/add");
    }

    @Test
    void validate_createsBidAndRedirects_whenValidationSucceeds() {
        BindingResult result = org.mockito.Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        BidListResponseDto bidList = new BidListResponseDto(null, "account", "type", 10.0);

        String viewName = bidListController.validate(bidList, result, new ExtendedModelMap());

        verify(bidListService).createBidList(bidList);
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
    }

    @Test
    void showUpdateForm_populatesModelAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        BidListResponseDto existing = new BidListResponseDto(9L, "account", "type", 10.0);
        BidListRequestDto requestDto = new BidListRequestDto("account", "type", 10.0);
        when(bidListService.findByIdBidList(9L)).thenReturn(existing);
        when(bidListMapper.toRequestDto(existing)).thenReturn(requestDto);

        String viewName = bidListController.showUpdateForm(9L, model);

        assertThat(viewName).isEqualTo("bidList/update");
        assertThat(model.asMap().get("bidList")).isEqualTo(requestDto);
        assertThat(model.asMap().get("bidListId")).isEqualTo(9L);
    }

    @Test
    void updateBid_returnsUpdateView_whenValidationFails() {
        Model model = new ExtendedModelMap();
        BindingResult result = org.mockito.Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String viewName = bidListController.updateBid(
                4L,
                new BidListRequestDto("account", "type", 10.0),
                result,
                model
        );

        assertThat(viewName).isEqualTo("bidList/update");
        assertThat(model.asMap().get("bidListId")).isEqualTo(4L);
    }

    @Test
    void updateBid_updatesBidAndRedirects_whenValidationSucceeds() {
        BindingResult result = org.mockito.Mockito.mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);
        BidListRequestDto requestDto = new BidListRequestDto("account", "type", 10.0);

        String viewName = bidListController.updateBid(4L, requestDto, result, new ExtendedModelMap());

        verify(bidListService).updateBidList(4L, requestDto);
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
    }

    @Test
    void deleteBid_deletesBidAndRedirects() {
        String viewName = bidListController.deleteBid(2L, new ExtendedModelMap());

        verify(bidListService).deleteBidList(2L);
        assertThat(viewName).isEqualTo("redirect:/bidList/list");
    }
}
