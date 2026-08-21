package com.nnk.springboot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.sql.Timestamp;

/**
 * JPA entity representing a Bid List.
 * 
 * Maps to the BIDLIST database table and represents a bid entry in the system
 * containing account, trading type, quantities, prices and other metadata.
 */
@Entity
@Table(name = "bidlist")
public class BidList {
    /** Unique identifier for the bid list entry */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BidListId")
    private Long bidListId;

    /** Account associated with the bid */
    @NotBlank(message = "Account is mandatory")
    @Column(name = "account", nullable = false, length = 30)
    private String account;

    /** Type of bid transaction */
    @NotBlank(message = "Type is mandatory")
    @Column(name = "type", nullable = false, length = 30)
    private String type;

    /** Quantity of bid */
    @Digits(integer = 8, fraction = 2, message = "Bid quantity must be a valid number with up to 2 decimal places")
    @Column(name = "bidQuantity")
    private Double bidQuantity;

    /** Quantity asked */
    @Digits(integer = 8, fraction = 2, message = "Ask quantity must be a valid number with up to 2 decimal places")
    @Column(name = "askQuantity")
    private Double askQuantity;

    /** Bid price */
    @Digits(integer = 8, fraction = 2, message = "Bid must be a valid number with up to 2 decimal places")
    @Column(name = "bid")
    private Double bid;

    /** Ask price */
    @Column(name = "ask")
    @Digits(integer = 8, fraction = 2, message = "Ask must be a valid number with up to 2 decimal places")
    private Double ask;

    /** Benchmark reference */
    @Column(name = "benchmark", length = 125)
    private String benchmark;

    /** Date of the bid list entry */
    @Column(name = "bidListDate")
    private Timestamp bidListDate;

    /** Commentary on the bid */
    @Column(name = "commentary", length = 125)
    private String commentary;

    /** Security type */
    @Column(name = "security", length = 125)
    private String security;

    /** Status of the bid */
    @Column(name = "status", length = 10)
    private String status;

    /** Trader name */
    @Column(name = "trader", length = 125)
    private String trader;

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
    public BidList(){   }

    /**
     * All-args constructor for BidList.
     *
     * @param bidListId the unique identifier
     * @param account the account
     * @param type the bid type
     * @param bidQuantity the bid quantity
     * @param askQuantity the ask quantity
     * @param bid the bid price
     * @param ask the ask price
     * @param benchmark the benchmark reference
     * @param bidListDate the bid list date
     * @param commentary the commentary
     * @param security the security type
     * @param status the bid status
     * @param trader the trader name
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
    public BidList(Long bidListId, String account, String type, Double bidQuantity, Double askQuantity, Double bid, Double ask, String benchmark, Timestamp bidListDate, String commentary, String security, String status, String trader, String book, String creationName, Timestamp creationDate, String revisionName, Timestamp revisionDate, String dealName, String dealType, String sourceListId, String side) {
        this.bidListId = bidListId;
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
        this.askQuantity = askQuantity;
        this.bid = bid;
        this.ask = ask;
        this.benchmark = benchmark;
        this.bidListDate = bidListDate;
        this.commentary = commentary;
        this.security = security;
        this.status = status;
        this.trader = trader;
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


    public Long getBidListId() {
        return bidListId;
    }

    public void setBidListId(Long bidListId) {
        this.bidListId = bidListId;
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

    public Double getBidQuantity() {
        return bidQuantity;
    }

    public void setBidQuantity(@NotNull @PositiveOrZero Double bidQuantity) {
        this.bidQuantity = bidQuantity;
    }

    public Double getAskQuantity() {
        return askQuantity;
    }

    public void setAskQuantity(@NotNull @PositiveOrZero Double askQuantity) {
        this.askQuantity = askQuantity;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public String getBenchmark() {
        return benchmark;
    }

    public void setBenchmark(String benchmark) {
        this.benchmark = benchmark;
    }

    public Timestamp getBidListDate() {
        return bidListDate;
    }

    public void setBidListDate(Timestamp bidListDate) {
        this.bidListDate = bidListDate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
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
