package com.stock.portfolio.adapters.alphavantage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ExchangeRateResponse {

    public class ExchangeRateData {
        @JsonProperty("1. From_Currency Code")
        private String fromCurrencyCode;

        @JsonProperty("3. To_Currency Code")
        private String toCurrencyCode;

        @JsonProperty("5. Exchange Rate")
        private BigDecimal exchangeRate;

        public String getFromCurrencyCode() {
            return fromCurrencyCode;
        }

        public void setFromCurrencyCode(String fromCurrencyCode) {
            this.fromCurrencyCode = fromCurrencyCode;
        }

        public String getToCurrencyCode() {
            return toCurrencyCode;
        }

        public void setToCurrencyCode(String toCurrencyCode) {
            this.toCurrencyCode = toCurrencyCode;
        }

        public BigDecimal getExchangeRate() {
            return exchangeRate;
        }

        public void setExchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
        }
    }

    @JsonProperty("Realtime Currency Exchange Rate")
    ExchangeRateData exchangeRateData;

    public ExchangeRateData getExchangeRateData() {
        return exchangeRateData;
    }

    public void setExchangeRate(ExchangeRateData exchangeRateData) {
        this.exchangeRateData = exchangeRateData;
    }
}
