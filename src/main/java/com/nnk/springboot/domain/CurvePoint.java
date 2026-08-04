package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * JPA entity representing a Curve Point.
 * 
 * Maps to the curvepoint database table and represents a point on a yield curve,
 * containing term and value information for interest rate curves.
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    /** Unique identifier for the curve point */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /** Identifier of the yield curve */
    @NotNull(message = "CurveId is mandatory")
    @Column(name = "CurveId")
    private Integer curveId;

    /** Date the curve point becomes effective */
    @Column(name = "asOfDate")
    private Timestamp asOfDate;

    /** Term of the yield curve point */
    @NotNull(message = "Term is mandatory")
    @Column(name = "term")
    private Double term;

    /** Value at this point on the yield curve */
    @NotNull(message = "Value is mandatory")
    @Column(name = "`value`")
    private Double value;

    /** Date of creation */
    @Column(name = "creationDate")
    private Timestamp creationDate;


    /**
     * Default constructor.
     */
    public CurvePoint() {
    }

    /**
     * All-args constructor for CurvePoint.
     *
     * @param id the unique identifier
     * @param curveId the yield curve identifier
     * @param asOfDate the effective date
     * @param term the term value
     * @param value the curve value
     * @param creationDate the creation date
     */
    public CurvePoint(Long id, Integer curveId, Timestamp asOfDate, Double term, Double value, Timestamp creationDate) {
        this.id = id;
        this.curveId = curveId;
        this.asOfDate = asOfDate;
        this.term = term;
        this.value = value;
        this.creationDate = creationDate;
    }

    // Getters and Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCurveId() {
        return curveId;
    }

    public void setCurveId(Integer curveId) {
        this.curveId = curveId;
    }

    public Timestamp getAsOfDate() {
        return asOfDate;
    }

    public void setAsOfDate(Timestamp asOfDate) {
        this.asOfDate = asOfDate;
    }

    public Double getTerm() {
        return term;
    }

    public void setTerm(Double term) {
        this.term = term;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
}
