package com.nnk.springboot.services;





import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;

import java.util.List;

public interface BidListService {

    List<BidListResponseDto> findAllBidList();
    BidListResponseDto findByIdBidList(Long id);
    BidListResponseDto createBidList(BidListRequestDto bidListRequestDto);
    BidListResponseDto updateBidList(Long id, BidListRequestDto bidListRequestDto);
    void deleteBidList(Long id);
}
