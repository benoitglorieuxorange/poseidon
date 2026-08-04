package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * JPA entity representing a Credit Rating.
 * 
 * Maps to the rating database table and contains credit rating information
 * from various rating agencies (Moody's, S&P, Fitch).
 */
@Entity
@Table(name = "rating")
public class Rating {

    /** Unique identifier for the rating */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /** Moody's credit rating */
    @NotBlank(message = "Moody's Rating is mandatory")
    @Column(name = "moodysRating", length = 125)
    private String moodysRating;

    /** Standard & Poor's credit rating */
    @NotBlank(message = "S&P Rating is mandatory")
    @Column(name = "sandPRating", length = 125)
    private String sandPRating;

    /** Fitch credit rating */
    @NotBlank(message = "Fitch Rating is mandatory")
    @Column(name = "fitchRating", length = 125)
    private String fitchRating;

    /** Rating order number */
    @NotNull(message = "Order number is mandatory")
    @Column(name = "orderNumber")
    private Integer orderNumber;


    /**
     * Default constructor.
     */
    public Rating() {
    }

    /**
     * All-args constructor for Rating.
     *
     * @param id the unique identifier
     * @param moodysRating the Moody's rating
     * @param sandPRating the S&P rating
     * @param fitchRating the Fitch rating
     * @param orderNumber the rating order number
     */
    public Rating(Long id, String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.id = id;
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMoodysRating() {
        return moodysRating;
    }

    public void setMoodysRating(String moodysRating) {
        this.moodysRating = moodysRating;
    }

    public String getSandPRating() {
        return sandPRating;
    }

    public void setSandPRating(String sandPRating) {
        this.sandPRating = sandPRating;
    }

    public String getFitchRating() {
        return fitchRating;
    }

    public void setFitchRating(String fitchRating) {
        this.fitchRating = fitchRating;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}