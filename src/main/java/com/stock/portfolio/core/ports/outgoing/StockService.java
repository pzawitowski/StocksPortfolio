package com.stock.portfolio.core.ports.outgoing;

import com.stock.portfolio.core.model.Stock;

import java.math.BigDecimal;
import java.util.List;

public interface StockService {

    List<Stock> findStocksByTicket(String ticket);

    BigDecimal getPriceByTicket(String ticket);

    BigDecimal getExchangeRate(String fromCurrency, String toCurrency);
}
