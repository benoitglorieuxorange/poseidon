package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dtos.TradeRequestDto;
import com.nnk.springboot.dtos.TradeResponseDto;
import com.nnk.springboot.mappers.TradeMapper;
import com.nnk.springboot.repositories.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeMapper tradeMapper;

    @InjectMocks
    private TradeServiceImpl tradeService;

    @Test
    void findAllTrades_returnsMappedDtos() {
        Trade first = trade(1, "account-1", "type-1");
        Trade second = trade(2, "account-2", "type-2");
        TradeResponseDto firstDto = tradeDto(1, "account-1", "type-1");
        TradeResponseDto secondDto = tradeDto(2, "account-2", "type-2");

        when(tradeRepository.findAll()).thenReturn(List.of(first, second));
        when(tradeMapper.toResponseDto(first)).thenReturn(firstDto);
        when(tradeMapper.toResponseDto(second)).thenReturn(secondDto);

        assertThat(tradeService.findAllTrades()).containsExactly(firstDto, secondDto);
    }

    @Test
    void findByIdTrade_returnsMappedDto() {
        Trade entity = trade(3, "account", "type");
        TradeResponseDto dto = tradeDto(3, "account", "type");

        when(tradeRepository.findById(3)).thenReturn(Optional.of(entity));
        when(tradeMapper.toResponseDto(entity)).thenReturn(dto);

        assertThat(tradeService.findByIdTrade(3)).isEqualTo(dto);
    }

    @Test
    void findByIdTrade_throwsWhenMissing() {
        when(tradeRepository.findById(7)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tradeService.findByIdTrade(7))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Trade not found with id: 7");
    }

    @Test
    void createTrade_savesMappedEntity() {
        TradeRequestDto request = tradeRequest("account", "type");
        Trade entity = trade(null, "account", "type");
        Trade saved = trade(10, "account", "type");
        TradeResponseDto dto = tradeDto(10, "account", "type");

        when(tradeMapper.toEntity(request)).thenReturn(entity);
        when(tradeRepository.save(entity)).thenReturn(saved);
        when(tradeMapper.toResponseDto(saved)).thenReturn(dto);

        assertThat(tradeService.createTrade(request)).isEqualTo(dto);
    }

    @Test
    void updateTrade_updatesEntity() {
        Trade existing = trade(5, "old-account", "old-type");
        TradeRequestDto request = tradeRequest("account", "type");
        TradeResponseDto dto = tradeDto(5, "account", "type");

        when(tradeRepository.findById(5)).thenReturn(Optional.of(existing));
        when(tradeRepository.save(existing)).thenReturn(existing);
        when(tradeMapper.toResponseDto(existing)).thenReturn(dto);

        assertThat(tradeService.updateTrade(5, request)).isEqualTo(dto);
        verify(tradeMapper).updateFromDto(request, existing);
    }

    @Test
    void updateTrade_throwsWhenMissing() {
        when(tradeRepository.findById(8)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tradeService.updateTrade(8, tradeRequest("account", "type")))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Trade not found with id: 8");
    }

    @Test
    void deleteTrade_deletesEntity() {
        Trade existing = trade(6, "account", "type");
        when(tradeRepository.findById(6)).thenReturn(Optional.of(existing));

        tradeService.deleteTrade(6);

        verify(tradeRepository).delete(existing);
    }

    @Test
    void deleteTrade_throwsWhenMissing() {
        when(tradeRepository.findById(9)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tradeService.deleteTrade(9))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Trade not found with id: 9");
    }

    private static Trade trade(Integer id, String account, String type) {
        return new Trade(id, account, type, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    private static TradeRequestDto tradeRequest(String account, String type) {
        return new TradeRequestDto(account, type, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }

    private static TradeResponseDto tradeDto(Integer id, String account, String type) {
        return new TradeResponseDto(id, account, type, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
}
