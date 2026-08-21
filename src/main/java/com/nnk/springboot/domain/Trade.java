package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;


/**
 * JPA entity representing a Trade.
 * 
 * Maps to the trade database table and represents a trade transaction
 * containing buy/sell quantities, prices, and associated metadata.
 */
@Entity
@Table(name = "trade")
public class Trade {

    /** Unique identifier for the trade */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TradeId")
    private Integer tradeId;

    /** Account associated with the trade */
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", nullable = false, length = 30)
    private String account;

    /** Type of trade transaction */
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", nullable = false, length = 30)
    private String type;

    /** Quantity to buy */
    @Digits(integer = 8, fraction = 2, message = "Buy quantity must be a valid number with up to 2 decimal places")
    @Column(name = "buyQuantity")
    private Double buyQuantity;

    /** Quantity to sell */
    @Digits(integer = 8, fraction = 2, message = "Sell quantity must be a valid number with up to 2 decimal places")
    @Column(name = "sellQuantity")
    private Double sellQuantity;

    /** Buy price */
    @Digits(integer = 8, fraction = 2, message = "Buy price must be a valid number with up to 2 decimal places")
    @Column(name = "buyPrice")
    private Double buyPrice;

    /** Sell price */
    @Digits(integer = 8, fraction = 2, message = "Sell price must be a valid number with up to 2 decimal places")
    @Column(name = "sellPrice")
    private Double sellPrice;

    /** Date of the trade */
    @Column(name = "tradeDate")
    private Timestamp tradeDate;

    /** Security type */
    @Column(name = "security", length = 125)
    private String security;

    /** Status of the trade */
    @Column(name = "status", length = 10)
    private String status;

    /** Trader name */
    @Column(name = "trader", length = 125)
    private String trader;

    /** Benchmark reference */
    @Column(name = "benchmark", length = 125)
    private String benchmark;

    /** Book reference */
    @Column(name = "book", length = 125)
    private String book;

    /** Name of the creator */
    @Column(name = "creationName", length = 125)
    private String creationName;

    /** Date of creation */
    @Column(name = "creationDate")
    private Timestamp creationDate;

    /** Name of the last reviewer */
    @Column(name = "revisionName", length = 125)
    private String revisionName;

    /** Date of last revision */
    @Column(name = "revisionDate")
    private Timestamp revisionDate;

    /** Deal name */
    @Column(name = "dealName", length = 125)
    private String dealName;

    /** Deal type */
    @Column(name = "dealType", length = 125)
    private String dealType;

    /** Source list identifier */
    @Column(name = "sourceListId", length = 125)
    private String sourceListId;

    /** Trade side (buy/sell) */
    @Column(name = "side", length = 125)
    private String side;


    /**
     * Default constructor.
     */
    public Trade(){}

    /**
     * All-args constructor for Trade.
     *
     * @param tradeId the unique identifier
     * @param account the account
     * @param type the trade type
     * @param buyQuantity the buy quantity
     * @param sellQuantity the sell quantity
     * @param buyPrice the buy price
     * @param sellPrice the sell price
     * @param tradeDate the trade date
     * @param security the security type
     * @param status the trade status
     * @param trader the trader name
     * @param benchmark the benchmark reference
     * @param book the book reference
     * @param creationName the creation user name
     * @param creationDate the creation date
     * @param revisionName the revision user name
     * @param revisionDate the revision date
     * @param dealName the deal name
     * @param dealType the deal type
     * @param sourceListId the source list id
     * @param side the trade side
     */
    public Trade(Integer tradeId, String account, String type, Double buyQuantity, Double sellQuantity, Double buyPrice, Double sellPrice, Timestamp tradeDate, String security, String status, String trader, String benchmark, String book, String creationName, Timestamp creationDate, String revisionName, Timestamp revisionDate, String dealName, String dealType, String sourceListId, String side) {
        this.tradeId = tradeId;
        this.account = account;
        this.type = type;
        this.buyQuantity = buyQuantity;
        this.sellQuantity = sellQuantity;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.tradeDate = tradeDate;
        this.security = security;
        this.status = status;
        this.trader = trader;
        this.benchmark = benchmark;
        this.book = book;
        this.creationName = creationName;
        this.creationDate = creationDate;
        this.revisionName = revisionName;
        this.revisionDate = revisionDate;
        this.dealName = dealName;
        this.dealType = dealType;
        this.sourceListId = sourceListId;
        this.side = side;
    }

    // Getters and Setters


    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getBuyQuantity() {
        return buyQuantity;
    }

    public void setBuyQuantity(Double buyQuantity) {
        this.buyQuantity = buyQuantity;
    }

    public Double getSellQuantity() {
        return sellQuantity;
    }

    public void setSellQuantity(Double sellQuantity) {
        this.sellQuantity = sellQuantity;
    }

    public Double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Timestamp getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Timestamp tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getCreationName() {
        return creationName;
    }

    public void setCreationName(String creationName) {
        this.creationName = creationName;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getRevisionName() {
        return revisionName;
    }

    public void setRevisionName(String revisionName) {
        this.revisionName = revisionName;
    }

    public Timestamp getRevisionDate() {
        return revisionDate;
    }

    public void setRevisionDate(Timestamp revisionDate) {
        this.revisionDate = revisionDate;
    }

    public String getDealName() {
        return dealName;
    }

    public void setDealName(String dealName) {
        this.dealName = dealName;
    }

    public String getDealType() {
        return dealType;
    }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public String getSourceListId() {
        return sourceListId;
    }

    public void setSourceListId(String sourceListId) {
        this.sourceListId = sourceListId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }
}
