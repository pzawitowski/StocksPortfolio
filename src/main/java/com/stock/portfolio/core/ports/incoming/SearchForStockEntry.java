package com.stock.portfolio.core.ports.incoming;

import com.stock.portfolio.core.model.StockEntry;

import java.util.List;

public interface SearchForStockEntry {
    List<StockEntry> findStockEntry(String ticket);
}
