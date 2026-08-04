package com.nnk.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public record TradeRequestDto(
        @NotBlank @Size(max = 30)
        String account,
        @NotBlank @Size(max = 30)
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
