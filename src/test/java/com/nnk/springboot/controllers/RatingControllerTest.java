package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.RatingRequestDto;
import com.nnk.springboot.dtos.RatingResponseDto;
import com.nnk.springboot.mappers.RatingMapper;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    @Mock
    private RatingService ratingService;

    @Mock
    private RatingMapper ratingMapper;

    @InjectMocks
    private RatingController ratingController;

    @Test
    void home_addsRatingsAndReturnsListView() {
        Model model = new ExtendedModelMap();
        List<RatingResponseDto> ratings = List.of(new RatingResponseDto(1L, "M", "S", "F", 1));
        when(ratingService.findAllRatings()).thenReturn(ratings);

        assertThat(ratingController.home(model)).isEqualTo("rating/list");
        assertThat(model.asMap().get("ratings")).isEqualTo(ratings);
    }

    @Test
    void addRatingForm_returnsAddView() {
        assertThat(ratingController.addRatingForm(new RatingRequestDto("M", "S", "F", 1))).isEqualTo("rating/add");
    }

    @Test
    void validate_returnsAddViewWhenValidationFails() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(ratingController.validate(new RatingRequestDto("M", "S", "F", 1), result, new ExtendedModelMap()))
                .isEqualTo("rating/add");
    }

    @Test
    void validate_createsRatingAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        RatingRequestDto request = new RatingRequestDto("M", "S", "F", 1);
        when(result.hasErrors()).thenReturn(false);

        assertThat(ratingController.validate(request, result, new ExtendedModelMap()))
                .isEqualTo("redirect:/rating/list");
        verify(ratingService).createRating(request);
    }

    @Test
    void showUpdateForm_populatesModelAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        RatingResponseDto response = new RatingResponseDto(2L, "M", "S", "F", 1);
        RatingRequestDto request = new RatingRequestDto("M", "S", "F", 1);
        when(ratingService.findByIdRating(2L)).thenReturn(response);
        when(ratingMapper.toRequestDto(response)).thenReturn(request);

        assertThat(ratingController.showUpdateForm(2L, model)).isEqualTo("rating/update");
        assertThat(model.asMap().get("rating")).isEqualTo(request);
        assertThat(model.asMap().get("ratingId")).isEqualTo(2L);
    }

    @Test
    void updateRating_returnsUpdateViewWhenValidationFails() {
        Model model = new ExtendedModelMap();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(ratingController.updateRating(3L, new RatingRequestDto("M", "S", "F", 1), result, model))
                .isEqualTo("rating/update");
        assertThat(model.asMap().get("ratingId")).isEqualTo(3L);
    }

    @Test
    void updateRating_updatesAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        RatingRequestDto request = new RatingRequestDto("M", "S", "F", 1);
        when(result.hasErrors()).thenReturn(false);

        assertThat(ratingController.updateRating(3L, request, result, new ExtendedModelMap()))
                .isEqualTo("redirect:/rating/list");
        verify(ratingService).updateRating(3L, request);
    }

    @Test
    void deleteRating_deletesAndRedirects() {
        assertThat(ratingController.deleteRating(4L, new ExtendedModelMap())).isEqualTo("redirect:/rating/list");
        verify(ratingService).deleteRating(4L);
    }
}
