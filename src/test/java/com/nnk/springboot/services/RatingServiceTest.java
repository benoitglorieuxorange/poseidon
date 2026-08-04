package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dtos.RatingRequestDto;
import com.nnk.springboot.dtos.RatingResponseDto;
import com.nnk.springboot.mappers.RatingMapper;
import com.nnk.springboot.repositories.RatingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceTest {

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RatingServiceImpl ratingService;

    @Test
    void findAllRatings_returnsMappedDtos() {
        Rating first = rating(1L, "M1", "S1", "F1", 1);
        Rating second = rating(2L, "M2", "S2", "F2", 2);
        RatingResponseDto firstDto = ratingDto(1L, "M1", "S1", "F1", 1);
        RatingResponseDto secondDto = ratingDto(2L, "M2", "S2", "F2", 2);

        when(ratingRepository.findAll()).thenReturn(List.of(first, second));
        when(ratingMapper.toResponseDto(first)).thenReturn(firstDto);
        when(ratingMapper.toResponseDto(second)).thenReturn(secondDto);

        assertThat(ratingService.findAllRatings()).containsExactly(firstDto, secondDto);
    }

    @Test
    void findByIdRating_returnsMappedDto() {
        Rating entity = rating(3L, "M", "S", "F", 3);
        RatingResponseDto dto = ratingDto(3L, "M", "S", "F", 3);

        when(ratingRepository.findById(3L)).thenReturn(Optional.of(entity));
        when(ratingMapper.toResponseDto(entity)).thenReturn(dto);

        assertThat(ratingService.findByIdRating(3L)).isEqualTo(dto);
    }

    @Test
    void findByIdRating_throwsWhenMissing() {
        when(ratingRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.findByIdRating(7L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rating not found with id: 7");
    }

    @Test
    void createRating_savesMappedEntity() {
        RatingRequestDto request = new RatingRequestDto("M", "S", "F", 4);
        Rating entity = rating(null, "M", "S", "F", 4);
        Rating saved = rating(10L, "M", "S", "F", 4);
        RatingResponseDto dto = ratingDto(10L, "M", "S", "F", 4);

        when(ratingMapper.toEntity(request)).thenReturn(entity);
        when(ratingRepository.save(entity)).thenReturn(saved);
        when(ratingMapper.toResponseDto(saved)).thenReturn(dto);

        assertThat(ratingService.createRating(request)).isEqualTo(dto);
    }

    @Test
    void updateRating_updatesEntity() {
        Rating existing = rating(5L, "M0", "S0", "F0", 1);
        RatingRequestDto request = new RatingRequestDto("M1", "S1", "F1", 2);
        RatingResponseDto dto = ratingDto(5L, "M1", "S1", "F1", 2);

        when(ratingRepository.findById(5L)).thenReturn(Optional.of(existing));
        when(ratingRepository.save(existing)).thenReturn(existing);
        when(ratingMapper.toResponseDto(existing)).thenReturn(dto);

        assertThat(ratingService.updateRating(5L, request)).isEqualTo(dto);
        verify(ratingMapper).updateFromDto(request, existing);
    }

    @Test
    void updateRating_throwsWhenMissing() {
        when(ratingRepository.findById(8L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.updateRating(8L, new RatingRequestDto("M", "S", "F", 1)))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rating not found with id: 8");
    }

    @Test
    void deleteRating_deletesEntity() {
        Rating existing = rating(6L, "M", "S", "F", 1);
        when(ratingRepository.findById(6L)).thenReturn(Optional.of(existing));

        ratingService.deleteRating(6L);

        verify(ratingRepository).delete(existing);
    }

    @Test
    void deleteRating_throwsWhenMissing() {
        when(ratingRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> ratingService.deleteRating(9L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Rating not found with id: 9");
    }

    private static Rating rating(Long id, String moodys, String sandp, String fitch, Integer orderNumber) {
        return new Rating(id, moodys, sandp, fitch, orderNumber);
    }

    private static RatingResponseDto ratingDto(Long id, String moodys, String sandp, String fitch, Integer orderNumber) {
        return new RatingResponseDto(id, moodys, sandp, fitch, orderNumber);
    }
}
