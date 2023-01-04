package com.stock.portfolio.core.ports.outgoing;

import com.stock.portfolio.core.model.Broker;
import com.stock.portfolio.core.model.StockEntry;

import java.util.List;

public interface StockPortfolioDatabase {
    Long addStockEntry(StockEntry entry);
    List<StockEntry> findStockEntry(String ticket);
    Broker getBroker(Long brokerId);
}
