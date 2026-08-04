package com.nnk.springboot.services;

import com.nnk.springboot.dtos.TradeRequestDto;
import com.nnk.springboot.dtos.TradeResponseDto;

import java.util.List;

public interface TradeService {

    List<TradeResponseDto> findAllTrades();
    TradeResponseDto findByIdTrade(Integer id);
    TradeResponseDto createTrade(TradeRequestDto tradeRequestDto);
    TradeResponseDto updateTrade(Integer id, TradeRequestDto tradeRequestDto);
    void deleteTrade(Integer id);
}
