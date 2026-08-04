package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dtos.BidListRequestDto;
import com.nnk.springboot.dtos.BidListResponseDto;
import com.nnk.springboot.mappers.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
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
class BidListServiceTest {

    @Mock
    private BidListRepository bidListRepository;

    @Mock
    private BidListMapper bidListMapper;

    @InjectMocks
    private BidListServiceImpl bidListService;

    @Test
    void findAllBidList_returnsMappedDtos() {
        BidList first = bidList(1L, "account-1", "type-1", 10.0);
        BidList second = bidList(2L, "account-2", "type-2", 20.0);
        BidListResponseDto firstDto = dto(1L, "account-1", "type-1", 10.0);
        BidListResponseDto secondDto = dto(2L, "account-2", "type-2", 20.0);

        when(bidListRepository.findAll()).thenReturn(List.of(first, second));
        when(bidListMapper.toResponseDto(first)).thenReturn(firstDto);
        when(bidListMapper.toResponseDto(second)).thenReturn(secondDto);

        List<BidListResponseDto> result = bidListService.findAllBidList();

        assertThat(result).containsExactly(firstDto, secondDto);
    }

    @Test
    void findByIdBidList_returnsMappedDto_whenEntityExists() {
        BidList entity = bidList(5L, "account", "type", 100.0);
        BidListResponseDto response = dto(5L, "account", "type", 100.0);

        when(bidListRepository.findById(5L)).thenReturn(Optional.of(entity));
        when(bidListMapper.toResponseDto(entity)).thenReturn(response);

        BidListResponseDto result = bidListService.findByIdBidList(5L);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void findByIdBidList_throws_whenEntityDoesNotExist() {
        when(bidListRepository.findById(9L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bidListService.findByIdBidList(9L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("BidList not found with id: 9");
    }

    @Test
    void createBidList_savesMappedEntityAndReturnsMappedResult() {
        BidListResponseDto request = dto(null, "new-account", "new-type", 42.0);
        BidList mappedEntity = bidList(null, "new-account", "new-type", 42.0);
        BidList savedEntity = bidList(11L, "new-account", "new-type", 42.0);
        BidListResponseDto response = dto(11L, "new-account", "new-type", 42.0);

        when(bidListMapper.toEntity(request)).thenReturn(mappedEntity);
        when(bidListRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(bidListMapper.toResponseDto(savedEntity)).thenReturn(response);

        BidListResponseDto result = bidListService.createBidList(request);

        assertThat(result).isEqualTo(response);
    }

    @Test
    void updateBidList_updatesEntityAndReturnsMappedResult_whenEntityExists() {
        BidList existing = bidList(3L, "old-account", "old-type", 5.0);
        BidListRequestDto request = new BidListRequestDto("updated-account", "updated-type", 15.0);
        BidListResponseDto response = dto(3L, "updated-account", "updated-type", 15.0);

        when(bidListRepository.findById(3L)).thenReturn(Optional.of(existing));
        when(bidListRepository.save(existing)).thenReturn(existing);
        when(bidListMapper.toResponseDto(existing)).thenReturn(response);

        BidListResponseDto result = bidListService.updateBidList(3L, request);

        verify(bidListMapper).updateFromDto(request, existing);
        assertThat(result).isEqualTo(response);
    }

    @Test
    void updateBidList_throws_whenEntityDoesNotExist() {
        BidListRequestDto request = new BidListRequestDto("account", "type", 1.0);
        when(bidListRepository.findById(7L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bidListService.updateBidList(7L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("BidList not found with id: 7");
    }

    @Test
    void deleteBidList_deletesEntity_whenEntityExists() {
        BidList existing = bidList(4L, "account", "type", 10.0);
        when(bidListRepository.findById(4L)).thenReturn(Optional.of(existing));

        bidListService.deleteBidList(4L);

        verify(bidListRepository).delete(existing);
    }

    @Test
    void deleteBidList_throws_whenEntityDoesNotExist() {
        when(bidListRepository.findById(12L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bidListService.deleteBidList(12L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("BidList not found with id: 12");
    }

    private static BidList bidList(Long id, String account, String type, Double bidQuantity) {
        BidList bidList = new BidList();
        bidList.setBidListId(id);
        bidList.setAccount(account);
        bidList.setType(type);
        bidList.setBidQuantity(bidQuantity);
        return bidList;
    }

    private static BidListResponseDto dto(Long id, String account, String type, Double bidQuantity) {
        return new BidListResponseDto(id, account, type, bidQuantity);
    }
}
