package com.nnk.springboot.dtos;

/**
 * DTO for BidList response.
 * 
 * Contains the essential fields of a bid list entity returned in API responses.
 *
 * @param bidListId the unique identifier
 * @param account the account
 * @param type the bid type
 * @param bidQuantity the bid quantity
 */
public record BidListResponseDto(
        Long bidListId,
        String account,
        String type,
        Double bidQuantity
) {}
