package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dtos.RatingRequestDto;
import com.nnk.springboot.dtos.RatingResponseDto;
import com.nnk.springboot.mappers.RatingMapper;
import com.nnk.springboot.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the RatingService interface.
 * Provides business logic for managing rating operations including
 * creating, reading, updating, and deleting rating entries.
 */

@Service
public class RatingServiceImpl implements RatingService{

    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
    }

    /**
     * Retrieves all ratings from the database.
     *
     * @return a list of RatingResponseDto containing all ratings
     */

    @Override
    public List<RatingResponseDto> findAllRatings() {
        return ratingRepository.findAll()
                .stream()
                .map(ratingMapper::toResponseDto)
                .toList();
    }

    /**
     *  Retrieves a specific rating by its ID.
     * @param id the rating ID
     * @return the RatingResponseDto of the found rating
     * @throws RuntimeException if no rating is found with the given ID
     */

    @Override
    public RatingResponseDto findByIdRating(Long id) {
        return ratingRepository.findById(id)
                .map(ratingMapper::toResponseDto)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
    }

    /**
     * Creates a new rating.
     *
     * @param ratingRequestDto the rating data to create
     * @return the created RatingResponseDto
     */

    @Override
    @Transactional
    public RatingResponseDto createRating(RatingRequestDto ratingRequestDto) {
        Rating rating = ratingMapper.toEntity(ratingRequestDto);
        Rating savedRating = ratingRepository.save(rating);
        return ratingMapper.toResponseDto(savedRating);
    }

    /**
     * Updates an existing rating.
     *
     * @param id the rating ID to update
     * @param ratingResponseDto the updated rating data
     * @return the updated RatingResponseDto
     * @throws RuntimeException if no rating is found with the given ID
     */

    @Override
    @Transactional
    public RatingResponseDto updateRating(Long id, RatingRequestDto ratingRequestDto) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        ratingMapper.updateFromDto(ratingRequestDto, rating);
        Rating updatedRating = ratingRepository.save(rating);
        return ratingMapper.toResponseDto(updatedRating);
    }

    /**
     * Deletes a rating by its ID.
     *
     * @param id the rating ID to delete
     * @throws RuntimeException if no rating is found with the given ID
     */

    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        ratingRepository.delete(rating);
    }
}
