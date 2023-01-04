package com.stock.portfolio.core.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class StockEntry {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate addedDate;
    private String ticket;
    private BigDecimal priceWhenAdded;
    private BigDecimal currentPrice;
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Broker broker;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(LocalDate addedDate) {
        this.addedDate = addedDate;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public BigDecimal getPriceWhenAdded() {
        return priceWhenAdded;
    }

    public void setPriceWhenAdded(BigDecimal priceWhenAdded) {
        this.priceWhenAdded = priceWhenAdded;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
