package com.nnk.springboot.controllers;

import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;
import com.nnk.springboot.mappers.CurvePointMapper;
import com.nnk.springboot.services.CurvePointService;
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
class CurveControllerTest {

    @Mock
    private CurvePointService curvePointService;

    @Mock
    private CurvePointMapper curvePointMapper;

    @InjectMocks
    private CurveController curveController;

    @Test
    void home_addsCurvePointsAndReturnsListView() {
        Model model = new ExtendedModelMap();
        List<CurvePointResponseDto> curvePoints = List.of(new CurvePointResponseDto(1L, 10, 1.0, 2.0));
        when(curvePointService.findAllCurvePoint()).thenReturn(curvePoints);

        assertThat(curveController.home(model)).isEqualTo("curvePoint/list");
        assertThat(model.asMap().get("curvePoints")).isEqualTo(curvePoints);
    }

    @Test
    void addBidForm_returnsAddView() {
        assertThat(curveController.addBidForm(new CurvePointRequestDto(10, 1.0, 2.0))).isEqualTo("curvePoint/add");
    }

    @Test
    void validate_returnsAddViewWhenValidationFails() {
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        assertThat(curveController.validate(new CurvePointRequestDto(10, 1.0, 2.0), result, new ExtendedModelMap()))
                .isEqualTo("curvePoint/add");
    }

    @Test
    void validate_createsCurvePointAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        CurvePointRequestDto request = new CurvePointRequestDto(10, 1.0, 2.0);
        when(result.hasErrors()).thenReturn(false);

        assertThat(curveController.validate(request, result, new ExtendedModelMap())).isEqualTo("redirect:/curvePoint/list");
        verify(curvePointService).createCurvePoint(request);
    }

    @Test
    void showUpdateForm_populatesModelAndReturnsUpdateView() {
        Model model = new ExtendedModelMap();
        CurvePointResponseDto response = new CurvePointResponseDto(2L, 10, 1.0, 2.0);
        CurvePointRequestDto request = new CurvePointRequestDto(10, 1.0, 2.0);
        when(curvePointService.findByIdCurvepoint(2L)).thenReturn(response);
        when(curvePointMapper.toRequestDto(response)).thenReturn(request);

        assertThat(curveController.showUpdateForm(2L, model)).isEqualTo("curvePoint/update");
        assertThat(model.asMap().get("curvePoint")).isEqualTo(request);
        assertThat(model.asMap().get("curvePointId")).isEqualTo(2L);
    }

    @Test
    void updateBid_returnsUpdateViewWhenValidationFails() {
        BindingResult result = mock(BindingResult.class);

        when(result.hasErrors()).thenReturn(true);

        assertThat(curveController.updateBid(3L, new CurvePointRequestDto(10, 1.0, 2.0), result, new ExtendedModelMap()))
                .isEqualTo("curvePoint/update");
    }

    @Test
    void updateBid_updatesAndRedirectsWhenValid() {
        BindingResult result = mock(BindingResult.class);
        CurvePointRequestDto request = new CurvePointRequestDto(10, 1.0, 2.0);
        when(result.hasErrors()).thenReturn(false);

        assertThat(curveController.updateBid(3L, request, result, new ExtendedModelMap())).isEqualTo("redirect:/curvePoint/list");
        verify(curvePointService).updateCurvePoint(3L, request);
    }

    @Test
    void deleteBid_deletesAndRedirects() {
        assertThat(curveController.deleteBid(4L, new ExtendedModelMap())).isEqualTo("redirect:/curvePoint/list");
        verify(curvePointService).deleteCurvePoint(4L);
    }
}
