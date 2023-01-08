package com.stock.portfolio.adapters.repository.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CurrencyEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String code;
    private String name;
    private BigDecimal dollarValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDollarValue() {
        return dollarValue;
    }

    public void setDollarValue(BigDecimal dollarValue) {
        this.dollarValue = dollarValue;
    }
}
