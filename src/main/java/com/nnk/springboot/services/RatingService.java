package com.nnk.springboot.services;

import com.nnk.springboot.dtos.RatingRequestDto;
import com.nnk.springboot.dtos.RatingResponseDto;

import java.util.List;

/**
 * Service interface for Rating operations.
 * 
 * Defines business logic operations for managing ratings including
 * creation, retrieval, update, and deletion.
 */
public interface RatingService {


    List<RatingResponseDto> findAllRatings();
    RatingResponseDto findByIdRating(Long id);
    RatingResponseDto createRating(RatingRequestDto ratingRequestDto);
    RatingResponseDto updateRating(Long id, RatingRequestDto ratingRequestDto);
    void deleteRating(Long id);
}
