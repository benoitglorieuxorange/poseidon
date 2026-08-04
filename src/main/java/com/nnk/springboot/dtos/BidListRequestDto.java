package com.nnk.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

/**
 * DTO for creating or updating a BidList.
 * 
 * Contains the essential fields required for a bid list request, validated
 * at the API boundary.
 *
 * @param account the account (required, max 30 characters)
 * @param type the bid type (required, max 30 characters)
 * @param bidQuantity the bid quantity (required, non-negative)
 */
public record BidListRequestDto(

       @NotBlank @Size(max = 30)
       String account,
       @NotBlank @Size(max = 30)
       String type,
       @NotNull @PositiveOrZero
       Double bidQuantity
) {}
