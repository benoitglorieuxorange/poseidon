package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BidListRepositoryTest {

    @Autowired
    private BidListRepository bidListRepository;

    @Test
    void bidListCrudOperations_workAsExpected() {
        BidList bidList = new BidList();
        bidList.setAccount("account");
        bidList.setType("type");
        bidList.setBidQuantity(100.0);

        BidList saved = bidListRepository.save(bidList);

        assertThat(saved.getBidListId()).isNotNull();

        Optional<BidList> fetched = bidListRepository.findById(saved.getBidListId());
        assertThat(fetched).isPresent();
        assertThat(fetched.get().getAccount()).isEqualTo("account");
        assertThat(fetched.get().getType()).isEqualTo("type");
        assertThat(fetched.get().getBidQuantity()).isEqualTo(100.0);

        bidListRepository.delete(fetched.get());

        assertThat(bidListRepository.findById(saved.getBidListId())).isNotPresent();
    }
}
