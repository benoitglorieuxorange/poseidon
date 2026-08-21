package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dtos.TradeRequestDto;
import com.nnk.springboot.dtos.TradeResponseDto;
import com.nnk.springboot.mappers.TradeMapper;
import com.nnk.springboot.repositories.TradeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradeServiceImpl implements TradeService {

    private final TradeRepository tradeRepository;
    private final TradeMapper tradeMapper;

    public TradeServiceImpl(TradeRepository tradeRepository, TradeMapper tradeMapper) {
        this.tradeRepository = tradeRepository;
        this.tradeMapper = tradeMapper;
    }

    @Override
    public List<TradeResponseDto> findAllTrades() {
        return tradeRepository.findAll()
                .stream()
                .map(tradeMapper::toResponseDto)
                .toList();
    }

    @Override
    public TradeResponseDto findByIdTrade(Integer id) {
        return tradeRepository.findById(id)
                .map(tradeMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Trade not found with id: " + id));
    }

    @Override
    @Transactional
    public TradeResponseDto createTrade(TradeRequestDto tradeRequestDto) {
        Trade trade = tradeMapper.toEntity(tradeRequestDto);
        Trade savedTrade = tradeRepository.save(trade);
        return tradeMapper.toResponseDto(savedTrade);
    }

    @Override
    @Transactional
    public TradeResponseDto updateTrade(Integer id, TradeRequestDto tradeRequestDto) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trade not found with id: " + id));
        tradeMapper.updateFromDto(tradeRequestDto, trade);
        Trade updatedTrade = tradeRepository.save(trade);
        return tradeMapper.toResponseDto(updatedTrade);
    }

    @Override
    public void deleteTrade(Integer id) {
        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trade not found with id: " + id));
        tradeRepository.delete(trade);
    }
}
