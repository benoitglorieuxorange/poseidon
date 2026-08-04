package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.TradeRequestDto;
import com.nnk.springboot.dtos.TradeResponseDto;
import com.nnk.springboot.mappers.TradeMapper;
import com.nnk.springboot.services.TradeService;
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
class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @Mock
    private TradeMapper tradeMapper;

    @InjectMocks
    private TradeController tradeController;

    @Test
    void home_addsTradesAndReturnsListView() {
        Model model = new ExtendedModelMap();
        List<TradeResponseDto> trades = List.of(tradeResponse(1, "account", "type"));
        when(tradeService.findAllTrades()).thenReturn(trades);

        assertThat(tradeController.home(model)).isEqualTo("trade/list");
        assertThat(model.asMap().get("trades")).isEqualTo(trades);
    }

    @Test
    void addUser_returnsAddView() {
        assertThat(tradeController.addUser(tradeRequest("account", "type"))).isEqualTo("trade/add");
    }

    @Test
    void validate_returnsAddViewWhenValidationFails() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(tradeController.validate(tradeRequest("account", "type"), result, new ExtendedModelMap())).isEqualTo("trade/add");
    }

    @Test
    void validate_createsTradeAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        TradeRequestDto request = tradeRequest("account", "type");
        when(result.hasErrors()).thenReturn(false);

        assertThat(tradeController.validate(request, result, new ExtendedModelMap())).isEqualTo("redirect:/trade/list");
        verify(tradeService).createTrade(request);
    }

    @Test
    void showUpdateForm_populatesModelAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        TradeResponseDto response = tradeResponse(2, "account", "type");
        TradeRequestDto request = tradeRequest("account", "type");
        when(tradeService.findByIdTrade(2)).thenReturn(response);
        when(tradeMapper.toRequestDto(response)).thenReturn(request);

        assertThat(tradeController.showUpdateForm(2, model)).isEqualTo("trade/update");
        assertThat(model.asMap().get("trade")).isEqualTo(request);
        assertThat(model.asMap().get("tradeId")).isEqualTo(2);
    }

    @Test
    void updateTrade_returnsUpdateViewWhenValidationFails() {
        Model model = new ExtendedModelMap();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(tradeController.updateTrade(3, tradeRequest("account", "type"), result, model)).isEqualTo("trade/update");
        assertThat(model.asMap().get("tradeId")).isEqualTo(3);
    }

    @Test
    void updateTrade_updatesAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        TradeRequestDto request = tradeRequest("account", "type");
        when(result.hasErrors()).thenReturn(false);

        assertThat(tradeController.updateTrade(3, request, result, new ExtendedModelMap())).isEqualTo("redirect:/trade/list");
        verify(tradeService).updateTrade(3, request);
    }

    @Test
    void deleteTrade_deletesAndRedirects() {
        assertThat(tradeController.deleteTrade(4, new ExtendedModelMap())).isEqualTo("redirect:/trade/list");
        verify(tradeService).deleteTrade(4);
    }

    private static TradeRequestDto tradeRequest(String account, String type) {
        return new TradeRequestDto(account, type, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    private static TradeResponseDto tradeResponse(Integer id, String account, String type) {
        return new TradeResponseDto(id, account, type, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
