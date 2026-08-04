package com.nnk.springboot.dtos;

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
   Integer orderNumber
) {}
