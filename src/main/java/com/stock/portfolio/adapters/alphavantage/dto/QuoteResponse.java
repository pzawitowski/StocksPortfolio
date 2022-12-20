package com.stock.portfolio.adapters.alphavantage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteResponse {

    @JsonProperty("Global Quote")
    private GlobalQuote globalQuote;

    public GlobalQuote getGlobalQuote() {
        return globalQuote;
    }

    public void setGlobalQuote(GlobalQuote globalQuote) {
        this.globalQuote = globalQuote;
    }
}
