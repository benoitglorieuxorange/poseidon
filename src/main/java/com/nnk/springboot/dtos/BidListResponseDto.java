package com.nnk.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

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
        @NotBlank @Size(max = 30)
        String account,
        @NotBlank @Size(max = 30)
        String type,
        Double bidQuantity
) {}
