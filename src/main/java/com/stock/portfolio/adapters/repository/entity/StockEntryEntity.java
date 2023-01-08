package com.stock.portfolio.adapters.repository.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class StockEntryEntity {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDate addedDate;
    private String ticket;
    private BigDecimal purchasePrice;
    private BigDecimal currentPrice;
    private Integer quantity;

    @JoinColumn(name = "currency_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private CurrencyEntity currency;

    @JoinColumn(name = "broker_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private BrokerEntity broker;

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

    public BrokerEntity getBroker() {
        return broker;
    }

    public void setBroker(BrokerEntity broker) {
        this.broker = broker;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
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

    public CurrencyEntity getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEntity currency) {
        this.currency = currency;
    }


}
