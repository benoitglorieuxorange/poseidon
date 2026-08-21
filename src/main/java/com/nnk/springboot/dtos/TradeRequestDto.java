package com.nnk.springboot.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

public record TradeRequestDto(
        @NotBlank @Size(max = 30)
        String account,
        @NotBlank @Size(max = 30)
        String type,
        @Digits(integer = 10, fraction = 3, message = "Buy quantity must contain only numbers")
        Double buyQuantity,
        @Digits(integer = 10, fraction = 3, message = "Sell quantity must contain only numbers")
        Double sellQuantity,
        @Digits(integer = 10, fraction = 3, message = "Buy price must contain only numbers")
        Double buyPrice,
        @Digits(integer = 10, fraction = 3, message = "Sell price must contain only numbers")
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
