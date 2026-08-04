package com.nnk.springboot.dtos;

import java.sql.Timestamp;

public record TradeResponseDto(
        Integer id,
        String account,
        String type,
        Double buyQuantity,
        Double sellQuantity,
        Double buyPrice,
        Double sellPrice,
        Timestamp tradeDate,
        String security,
        String status,
        String trader,
        String benchmark,
        String book,
        String creationName,
        Timestamp creationDate,
        String revisionName,
        Timestamp revisionDate,
        String dealName,
        String dealType,
        String sourceListId,
        String side
) {}
