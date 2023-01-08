package com.stock.portfolio.core.model;

public class Broker {
    private Long id;
    private String brokerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Broker(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }
}
