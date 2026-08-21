package com.nnk.springboot.seeder;

import com.nnk.springboot.repositories.*;
import com.nnk.springboot.domain.*;
import com.nnk.springboot.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final BidListRepository bidListRepository;
    private final TradeRepository tradeRepository;
    private final CurvePointRepository curvePointRepository;
    private final RatingRepository ratingRepository;
    private final RuleNameRepository ruleNameRepository;

    public DataSeeder(BidListRepository bidListRepository,
                      TradeRepository tradeRepository,
                      CurvePointRepository curvePointRepository,
                      RatingRepository ratingRepository,
                      RuleNameRepository ruleNameRepository) {
        this.bidListRepository = bidListRepository;
        this.tradeRepository = tradeRepository;
        this.curvePointRepository = curvePointRepository;
        this.ratingRepository = ratingRepository;
        this.ruleNameRepository = ruleNameRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        clearTables();
        seedBidList();
        seedTrade();
        seedCurvePoint();
        seedRating();
        seedRuleName();
    }

    @Transactional
    protected void clearTables() {
        bidListRepository.deleteAllInBatch();
        tradeRepository.deleteAllInBatch();
        curvePointRepository.deleteAllInBatch();
        ratingRepository.deleteAllInBatch();
        ruleNameRepository.deleteAllInBatch();
    }



    private void seedBidList() {
        if (bidListRepository.count() > 0) {
            return;
        }

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        BidList bid1 = new BidList();
        bid1.setAccount("Account1");
        bid1.setType("Type1");
        bid1.setBidQuantity(10.0);
        bid1.setAskQuantity(15.0);
        bid1.setBid(100.5);
        bid1.setAsk(101.5);
        bid1.setBenchmark("Benchmark1");
        bid1.setBidListDate(now);
        bid1.setCommentary("Seed commentary 1");
        bid1.setSecurity("Security1");
        bid1.setStatus("Open");
        bid1.setTrader("Trader1");
        bid1.setBook("Book1");
        bid1.setCreationName("seeder");
        bid1.setCreationDate(now);

        BidList bid2 = new BidList();
        bid2.setAccount("Account2");
        bid2.setType("Type2");
        bid2.setBidQuantity(20.0);
        bid2.setAskQuantity(25.0);
        bid2.setBid(200.5);
        bid2.setAsk(201.5);
        bid2.setBenchmark("Benchmark2");
        bid2.setBidListDate(now);
        bid2.setCommentary("Seed commentary 2");
        bid2.setSecurity("Security2");
        bid2.setStatus("Open");
        bid2.setTrader("Trader2");
        bid2.setBook("Book2");
        bid2.setCreationName("seeder");
        bid2.setCreationDate(now);

        bidListRepository.saveAll(List.of(bid1, bid2));
    }

    private void seedTrade() {
        if (tradeRepository.count() > 0) {
            return;
        }

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        Trade trade1 = new Trade();
        trade1.setAccount("Account1");
        trade1.setType("Type1");
        trade1.setBuyQuantity(50.0);
        trade1.setSellQuantity(45.0);
        trade1.setBuyPrice(10.5);
        trade1.setSellPrice(11.0);
        trade1.setTradeDate(now);
        trade1.setSecurity("Security1");
        trade1.setStatus("Open");
        trade1.setTrader("Trader1");
        trade1.setBenchmark("Benchmark1");
        trade1.setBook("Book1");
        trade1.setCreationName("seeder");
        trade1.setCreationDate(now);

        Trade trade2 = new Trade();
        trade2.setAccount("Account2");
        trade2.setType("Type2");
        trade2.setBuyQuantity(60.0);
        trade2.setSellQuantity(55.0);
        trade2.setBuyPrice(20.5);
        trade2.setSellPrice(21.0);
        trade2.setTradeDate(now);
        trade2.setSecurity("Security2");
        trade2.setStatus("Open");
        trade2.setTrader("Trader2");
        trade2.setBenchmark("Benchmark2");
        trade2.setBook("Book2");
        trade2.setCreationName("seeder");
        trade2.setCreationDate(now);

        tradeRepository.saveAll(List.of(trade1, trade2));
    }

    private void seedCurvePoint() {
        if (curvePointRepository.count() > 0) {
            return;
        }

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());

        CurvePoint cp1 = new CurvePoint();
        cp1.setCurveId(10);
        cp1.setAsOfDate(now);
        cp1.setTerm(1.0);
        cp1.setValue(100.0);
        cp1.setCreationDate(now);

        CurvePoint cp2 = new CurvePoint();
        cp2.setCurveId(20);
        cp2.setAsOfDate(now);
        cp2.setTerm(2.0);
        cp2.setValue(200.0);
        cp2.setCreationDate(now);

        curvePointRepository.saveAll(List.of(cp1, cp2));
    }

    private void seedRating() {
        if (ratingRepository.count() > 0) {
            return;
        }

        Rating r1 = new Rating();
        r1.setMoodysRating("Aaa");
        r1.setSandPRating("AAA");
        r1.setFitchRating("AAA");
        r1.setOrderNumber(1);

        Rating r2 = new Rating();
        r2.setMoodysRating("Aa1");
        r2.setSandPRating("AA+");
        r2.setFitchRating("AA+");
        r2.setOrderNumber(2);

        ratingRepository.saveAll(List.of(r1, r2));
    }

    private void seedRuleName() {
        if (ruleNameRepository.count() > 0) {
            return;
        }

        RuleName rn1 = new RuleName();
        rn1.setName("Rule1");
        rn1.setDescription("Description rule 1");
        rn1.setJson("{}");
        rn1.setTemplate("Template1");
        rn1.setSqlStr("SELECT * FROM table1");
        rn1.setSqlPart("part1");

        ruleNameRepository.save(rn1);
    }
}
