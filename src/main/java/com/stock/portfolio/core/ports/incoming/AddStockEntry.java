package com.stock.portfolio.core.ports.incoming;

import com.stock.portfolio.core.model.StockEntry;

public interface AddStockEntry {
    Long addStockEntry(StockEntry stockEntryCommand);
}
