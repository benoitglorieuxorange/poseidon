package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import com.nnk.springboot.mappers.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the BidListService interface.
 * Provides business logic for managing bid list operations including
 * creating, reading, updating, and deleting bid list entries.
 */

@Service
public class BidListServiceImpl implements BidListService {

    private final BidListRepository bidListRepository;
    private final BidListMapper bidListMapper;

    /**
     * Constructs a BidListServiceImpl with the required dependencies.
     *
     * @param bidListRepository the repository for BidList database operations
     * @param bidListMapper the mapper for converting between entities and DTOs
     */

    public BidListServiceImpl (BidListRepository bidListRepository, BidListMapper bidListMapper) {
        this.bidListRepository = bidListRepository;
        this.bidListMapper = bidListMapper;
    }


    /**
     * Retrieves all bid list entries from the database.
     *
     * @return a list of BidListResponseDto containing all bid list entries
     */

    @Override
    public List<BidListResponseDto> findAllBidList() {
        return bidListRepository.findAll()
                .stream()
                .map(bidListMapper::toResponseDto)
                .toList();
    }

    /**
     * Retrieves a specific bid list entry by its ID.
     *
     * @param id the ID of the bid list entry to retrieve
     * @return the BidListResponseDto of the found bid list entry
     * @throws RuntimeException if no bid list entry is found with the given ID
     */

    @Override
    public BidListResponseDto findByIdBidList(Long id) {
        BidList bidlist = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("BidList not found with id: " + id));
        return bidListMapper.toResponseDto(bidlist);
    }

    /**
     * Creates a new bid list entry.
     *
     * @param bidListResponseDto the bid list data to create
     * @return the created BidListResponseDto with generated ID
     */

    @Override
    public BidListResponseDto createBidList(BidListResponseDto bidListResponseDto) {
        BidList bidList = bidListMapper.toEntity(bidListResponseDto);
        BidList savedBidList = bidListRepository.save(bidList);
        return bidListMapper.toResponseDto(savedBidList);
    }

    /**
     * Updates an existing bid list entry.
     *
     * @param id the ID of the bid list entry to update
     * @param bidListRequestDto the updated bid list data
     * @return the updated BidListResponseDto
     * @throws RuntimeException if no bid list entry is found with the given ID
     */

    @Override
    public BidListResponseDto updateBidList(Long id, BidListRequestDto bidListRequestDto) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("BidList not found with id: " + id));
        bidListMapper.updateFromDto(bidListRequestDto, bidList);
        BidList updatedBidList = bidListRepository.save(bidList);
        return bidListMapper.toResponseDto(updatedBidList);
    }

    /**
     * Deletes a bid list entry by its ID.
     *
     * @param id the ID of the bid list entry to delete
     * @throws RuntimeException if no bid list entry is found with the given ID
     */

    @Override
    public void deleteBidList(Long id) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new RuntimeException("BidList not found with id: " + id));
        bidListRepository.delete(bidList);
    }
}
