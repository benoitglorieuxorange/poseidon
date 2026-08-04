package com.nnk.springboot.dtos;

/**
 * DTO for Rating response.
 * 
 * Contains the fields of a rating entity returned in API responses.
 *
 * @param id the unique identifier
 * @param moodysRating the Moody's rating
 * @param sandPRating the S&P rating
 * @param fitchRating the Fitch rating
 * @param orderNumber the rating order number
 */
public record RatingResponseDto(
        Long id,
        String moodysRating,
        String sandPRating,
        String fitchRating,
        Integer orderNumber
) {}
