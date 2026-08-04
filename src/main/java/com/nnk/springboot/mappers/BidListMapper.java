package com.nnk.springboot.mappers;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between BidList entity and DTOs.
 * 
 * Handles conversion between BidList JPA entities and their corresponding
 * request/response DTOs for API layer operations.
 */
@Component
public class BidListMapper {

    /**
     * Converts a BidList entity to a BidListResponseDto.
     *
     * @param bidList the BidList entity to be converted
     * @return a BidListResponseDto containing the same values as the provided entity, or null if input is null
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

    /**
     * Converts a BidListResponseDto to a BidList entity.
     *
     * @param dto the BidListResponseDto to be converted
     * @return a new BidList entity containing the same values as the provided DTO, or null if input is null
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


    /**
     * Updates an existing BidList entity with values from a BidListRequestDto.
     *
     * @param dto the BidListRequestDto containing the new values
     * @param bidList the existing BidList entity to be updated
     */
    public void updateFromDto (BidListRequestDto dto, BidList bidList){
       bidList.setAccount(dto.account());
       bidList.setType(dto.type());
       bidList.setBidQuantity(dto.bidQuantity());
    }

    /**
     * Converts a BidListResponseDto to a BidListRequestDto.
     *
     * @param responseDto the BidListResponseDto to be converted
     * @return a new BidListRequestDto containing the same values as the provided DTO, or null if input is null
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
