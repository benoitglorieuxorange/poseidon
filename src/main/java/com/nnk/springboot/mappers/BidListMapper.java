package com.nnk.springboot.mappers;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BidListMapper {

    public BidListResponseDto toResponseDto(BidList bidList){
        if (bidList == null){
            return null;
        }
        return new BidListResponseDto(
            bidList.getBidListId(),
            bidList.getAccount(),
            bidList.getType(),
            bidList.getBidQuantity()
        );
    }

    public BidList toEntity(BidListResponseDto dto){
        if (dto == null){
            return null;
        }
        BidList bidList = new BidList();
        bidList.setBidListId(dto.bidListId());
        bidList.setAccount(dto.account());
        bidList.setType(dto.type());
        bidList.setBidQuantity(dto.bidQuantity());
        return bidList;
    }

    public void updateFromDto (BidListRequestDto dto, BidList bidList){
        bidList.setAccount(dto.account());
        bidList.setType(dto.type());
        bidList.setBidQuantity(dto.bidQuantity());
    }

    public BidListRequestDto toRequestDto(BidListResponseDto responseDto){
        if (responseDto == null){
            return null;
        }
        return new BidListRequestDto(
                responseDto.account(),
                responseDto.type(),
                responseDto.bidQuantity()
        );
    }
}
