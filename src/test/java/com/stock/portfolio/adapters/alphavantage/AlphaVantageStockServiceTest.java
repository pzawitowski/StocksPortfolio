package com.stock.portfolio.adapters.alphavantage;

import com.stock.portfolio.core.model.Stock;
import com.stock.portfolio.core.ports.outgoing.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AlphaVantageStockServiceTest {

    @Autowired
    StockService stockService;

    @Test
    void whenSearchForTescoShouldReturnThreeMatches() {
        List<Stock> stocks = stockService.findStocksByTicket("tesco");

        assertThat(stocks).hasSize(3);
    }


    @Test
    void whenQuoteForIBMPriceShouldReturnNumber() {
        BigDecimal price = stockService.getPriceByTicket("IBM");

        assertThat(price).isNotNull();
    }

    @Test
    void whenQueryForExchangeShouldReturnPositiveNumber() {
        BigDecimal exchangeRate = stockService.getExchangeRate("USD", "PLN");

        assertThat(exchangeRate).isPositive();
    }
}