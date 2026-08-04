package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TradeRepositoryTest {

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    void tradeCrudOperations_workAsExpected() {
        Trade trade = new Trade(null, "account", "type", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);

        Trade saved = tradeRepository.save(trade);

        assertThat(saved.getTradeId()).isNotNull();
        assertThat(tradeRepository.findById(saved.getTradeId())).isPresent();
        assertThat(tradeRepository.findById(saved.getTradeId()).get().getAccount()).isEqualTo("account");

        tradeRepository.deleteById(saved.getTradeId());

        assertThat(tradeRepository.findById(saved.getTradeId())).isNotPresent();
    }
}
