package com.nnk.springboot.mappers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dtos.TradeRequestDto;
import com.nnk.springboot.dtos.TradeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TradeMapper {

    public TradeResponseDto toResponseDto(Trade trade) {
        if (trade == null) {
            return null;
        }
        return new TradeResponseDto(
                trade.getTradeId(),
                trade.getAccount(),
                trade.getType(),
                trade.getBuyQuantity(),
                trade.getSellQuantity(),
                trade.getBuyPrice(),
                trade.getSellPrice(),
                trade.getTradeDate(),
                trade.getSecurity(),
                trade.getStatus(),
                trade.getTrader(),
                trade.getBenchmark(),
                trade.getBook(),
                trade.getCreationName(),
                trade.getCreationDate(),
                trade.getRevisionName(),
                trade.getRevisionDate(),
                trade.getDealName(),
                trade.getDealType(),
                trade.getSourceListId(),
                trade.getSide()
        );
    }

    public Trade toEntity(TradeRequestDto dto) {
        if (dto == null) {
            return null;
        }
        Trade trade = new Trade();
        updateFromDto(dto, trade);
        return trade;
    }

    public void updateFromDto(TradeRequestDto dto, Trade trade) {
        if (dto == null || trade == null) {
            return;
        }
        trade.setAccount(dto.account());
        trade.setType(dto.type());
        trade.setBuyQuantity(dto.buyQuantity());
        trade.setSellQuantity(dto.sellQuantity());
        trade.setBuyPrice(dto.buyPrice());
        trade.setSellPrice(dto.sellPrice());
        trade.setTradeDate(dto.tradeDate());
        trade.setSecurity(dto.security());
        trade.setStatus(dto.status());
        trade.setTrader(dto.trader());
        trade.setBenchmark(dto.benchmark());
        trade.setBook(dto.book());
        trade.setCreationName(dto.creationName());
        trade.setCreationDate(dto.creationDate());
        trade.setRevisionName(dto.revisionName());
        trade.setRevisionDate(dto.revisionDate());
        trade.setDealName(dto.dealName());
        trade.setDealType(dto.dealType());
        trade.setSourceListId(dto.sourceListId());
        trade.setSide(dto.side());
    }

    public TradeRequestDto toRequestDto(TradeResponseDto responseDto) {
        if (responseDto == null) {
            return null;
        }
        return new TradeRequestDto(
                responseDto.account(),
                responseDto.type(),
                responseDto.buyQuantity(),
                responseDto.sellQuantity(),
                responseDto.buyPrice(),
                responseDto.sellPrice(),
                responseDto.tradeDate(),
                responseDto.security(),
                responseDto.status(),
                responseDto.trader(),
                responseDto.benchmark(),
                responseDto.book(),
                responseDto.creationName(),
                responseDto.creationDate(),
                responseDto.revisionName(),
                responseDto.revisionDate(),
                responseDto.dealName(),
                responseDto.dealType(),
                responseDto.sourceListId(),
                responseDto.side()
        );
    }
}
