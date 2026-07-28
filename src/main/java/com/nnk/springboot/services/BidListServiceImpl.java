package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import com.nnk.springboot.mappers.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;
    private final BidListMapper bidListMapper;

    public BidListServiceImpl (BidListRepository bidListRepository, BidListMapper bidListMapper) {
        this.bidListRepository = bidListRepository;
        this.bidListMapper = bidListMapper;
    }

    @Override
    public List<BidListResponseDto> findAllBidList() {
        return bidListRepository.findAll()
                .stream()
                .map(bidListMapper::toResponseDto)
                .toList();
    }

    @Override
    public BidListResponseDto findByIdBidList(Long id) {
        BidList bidlist = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("BidList not found with id: " + id));
        return bidListMapper.toResponseDto(bidlist);
    }

    @Override
    public BidListResponseDto createBidList(BidListRequestDto bidListRequestDto) {
        BidList bidList = new BidList();
        bidListMapper.updateFromDto(bidListRequestDto, bidList);
        BidList savedBidList = bidListRepository.save(bidList);
        return bidListMapper.toResponseDto(savedBidList);
    }

    @Override
    public BidListResponseDto updateBidList(Long id, BidListRequestDto bidListRequestDto) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("BidList not found with id: " + id));
        bidListMapper.updateFromDto(bidListRequestDto, bidList);
        BidList updatedBidList = bidListRepository.save(bidList);
        return bidListMapper.toResponseDto(updatedBidList);
    }

    @Override
    public void deleteBidList(Long id) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("BidList not found with id: " + id));
        bidListRepository.delete(bidList);
    }
}
