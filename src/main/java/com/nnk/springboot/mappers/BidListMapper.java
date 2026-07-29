package com.nnk.springboot.mappers;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import org.springframework.stereotype.Component;

@Component
public class BidListMapper {

    /*
        * Convert a BidList entity to a BidListResponseDto.
        * This method is useful for scenarios where you need to transform entity data into a response format for API responses.
        *
        * @param bidList the BidList entity to be converted
        * @return a new BidListResponseDto containing the same values as the provided bidList
    */

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

    /*
        * Convert a BidListRequestDto to a BidList entity.
        * This method is useful for scenarios where you need to transform request data into an entity format for persistence.
        *
        * @param dto the BidListRequestDto to be converted
        * @return a new BidList entity containing the same values as the provided dto
     */

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


    /*
        * Update an existing BidList entity with values from a BidListRequestDto.
        * This method does not return a value; it modifies the provided BidList entity in place.
        *
        * @param dto the BidListRequestDto containing the new values
        * @param bidList the existing BidList entity to be updated
    */

    public void updateFromDto (BidListRequestDto dto, BidList bidList){
        bidList.setAccount(dto.account());
        bidList.setType(dto.type());
        bidList.setBidQuantity(dto.bidQuantity());
    }

    /*
        * Convert a BidListResponseDto to a BidListRequestDto.
        * This method is useful for scenarios where you need to transform response data back into a request format.
        *
        * @param responseDto the BidListResponseDto to be converted
        * @return a new BidListRequestDto containing the same values as the provided responseDto
     */

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
