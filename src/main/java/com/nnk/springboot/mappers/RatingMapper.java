package com.nnk.springboot.mappers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dtos.RatingRequestDto;
import com.nnk.springboot.dtos.RatingResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between Rating entity and DTOs.
 * 
 * Handles conversion between Rating JPA entities and their corresponding
 * request/response DTOs for API layer operations.
 */
@Component
public class RatingMapper {

    /**
     * Converts a Rating entity to a RatingResponseDto.
     *
     * @param rating the Rating entity to be converted
     * @return a RatingResponseDto containing the same values as the provided entity, or null if input is null
     */
    public RatingResponseDto toResponseDto(Rating rating){
       if (rating == null){
           return null;
       }
       return new RatingResponseDto(
           rating.getId(),
           rating.getMoodysRating(),
           rating.getSandPRating(),
           rating.getFitchRating(),
           rating.getOrderNumber()
       );
    }

    /**
     * Converts a RatingResponseDto to a Rating entity.
     *
     * @param ratingResponseDto the RatingResponseDto to be converted
     * @return a new Rating entity containing the same values as the provided DTO, or null if input is null
     */
    public Rating toEntity(RatingRequestDto dto){
       if (dto == null){
           return null;
       }
        Rating rating = new Rating();
        rating.setMoodysRating(dto.moodysRating());
        rating.setSandPRating(dto.sandPRating());
        rating.setFitchRating(dto.fitchRating());
        rating.setOrderNumber(dto.orderNumber());
        return rating;
    }

    /**
     * Updates an existing Rating entity with values from a RatingRequestDto.
     *
     * @param dto the RatingRequestDto containing the new values
     * @param rating the existing Rating entity to be updated
     */
    public void updateFromDto(RatingRequestDto dto, Rating rating){
       if(dto == null || rating == null){
           return;
       }
       rating.setFitchRating(dto.fitchRating());
       rating.setMoodysRating(dto.moodysRating());
       rating.setSandPRating(dto.sandPRating());
       rating.setOrderNumber(dto.orderNumber());
    }

    /**
     * Converts a RatingResponseDto to a RatingRequestDto.
     *
     * @param ratingResponseDto the RatingResponseDto to be converted
     * @return a new RatingRequestDto containing the same values as the provided DTO, or null if input is null
     */
    public RatingRequestDto toRequestDto(RatingResponseDto ratingResponseDto){
       if (ratingResponseDto == null){
           return null;
       }
       return new RatingRequestDto(
           ratingResponseDto.moodysRating(),
           ratingResponseDto.sandPRating(),
           ratingResponseDto.fitchRating(),
           ratingResponseDto.orderNumber()
       );
    }
}
