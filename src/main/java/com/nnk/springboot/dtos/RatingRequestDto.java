package com.nnk.springboot.dtos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for creating or updating a Rating.
 * 
 * Contains the essential fields required for a rating request.
 *
 * @param moodysRating the Moody's rating
 * @param sandPRating the S&P rating
 * @param fitchRating the Fitch rating
 * @param orderNumber the rating order number
 */
public record RatingRequestDto(
   String moodysRating,
   String sandPRating,
   String fitchRating,
   @NotNull(message = "Order number is mandatory")
   @Digits(integer = 10, fraction = 0, message = "Order number must contain only numbers")
   Integer orderNumber
) {}
