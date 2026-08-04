package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * JPA entity representing a Rule Name.
 * 
 * Maps to the rulename database table and contains rule definitions and configurations
 * for risk management and compliance checking.
 */
@Entity
@Table(name = "rulename")
public class RuleName {

    /** Unique identifier for the rule */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    /** Name of the rule */
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name", length = 125)
    private String name;

    /** Description of the rule */
    @NotBlank(message = "Description is mandatory")
    @Column(name = "description", length = 125)
    private String description;

    /** JSON configuration for the rule */
    @NotBlank(message = "Json is mandatory")
    @Column(name = "json", length = 125)
    private String json;

    /** Template for the rule */
    @NotBlank(message = "Template is mandatory")
    @Column(name = "template", length = 512)
    private String template;

    /** SQL string component */
    @NotBlank(message = "SQL Str is mandatory")
    @Column(name = "sqlStr", length = 125)
    private String sqlStr;

    /** SQL part component */
    @NotBlank(message = "SQL Part is mandatory")
    @Column(name = "sqlPart", length = 125)
    private String sqlPart;

    /**
     * Default constructor.
     */
    public RuleName() {
    }

    /**
     * All-args constructor for RuleName.
     *
     * @param id the unique identifier
     * @param name the rule name
     * @param description the rule description
     * @param json the JSON configuration
     * @param template the rule template
     * @param sqlStr the SQL string component
     * @param sqlPart the SQL part component
     */
    public RuleName(Long id, String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}