package com.nnk.springboot.dtos;

public record BidListResponseDto(
        Long bidListId,
        String account,
        String type,
        Double bidQuantity
) {}
