package com.nnk.springboot.mappers;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dtos.CurvePointRequestDto;
import com.nnk.springboot.dtos.CurvePointResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CurvePointMapper {

    public CurvePointResponseDto toResponseDto(CurvePoint curvePoint) {
        if (curvePoint == null) {
            return null;
        }
        return new CurvePointResponseDto(
                curvePoint.getId(),
                curvePoint.getCurveId(),
                curvePoint.getTerm(),
                curvePoint.getValue()
        );
    }

    public CurvePoint toEntity(CurvePointRequestDto dto) {
        if (dto == null) {
            return null;
        }
        CurvePoint curvePoint = new CurvePoint();
        curvePoint.setCurveId(dto.curveId());
        curvePoint.setTerm(dto.term());
        curvePoint.setValue(dto.value());
        return curvePoint;
    }

    public void updateFromDto (CurvePointRequestDto dto, CurvePoint curvePoint) {
        if (dto == null || curvePoint == null) {
            return;
        }
        curvePoint.setCurveId(dto.curveId());
        curvePoint.setTerm(dto.term());
        curvePoint.setValue(dto.value());
    }

    public CurvePointRequestDto toRequestDto(CurvePointResponseDto responseDto) {
        if (responseDto == null) {
            return null;
        }
        return new CurvePointRequestDto(
                responseDto.curveId(),
                responseDto.term(),
                responseDto.value()
        );
    }


}
