package com.stock.portfolio.adapters.alphavantage;

import com.stock.portfolio.core.model.Stock;
import com.stock.portfolio.core.ports.outgoing.StockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AlphaVantageStockServiceTest {

    @Autowired
    StockService stockService;

    @Test
    public void whenSearchForTescoShouldReturnThreeMatches() {
        List<Stock> stocks = stockService.findStocksByTicket("tesco");

        assertEquals(3, stocks.size());
    }


    @Test
    public void whenQuoteForIBMPriceShouldReturnNumber() {
        BigDecimal price = stockService.getPriceByTicket("IBM");

        assertNotNull(price);
    }
}