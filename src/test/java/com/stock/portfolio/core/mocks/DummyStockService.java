package com.stock.portfolio.core.mocks;

import com.stock.portfolio.core.model.Stock;
import com.stock.portfolio.core.ports.outgoing.StockService;

import java.math.BigDecimal;
import java.util.List;

public class DummyStockService implements StockService {
    @Override
    public List<Stock> findStocksByTicket(String ticket) {
        return null;
    }

    @Override
    public BigDecimal getPriceByTicket(String ticket) {
        return null;
    }
}
