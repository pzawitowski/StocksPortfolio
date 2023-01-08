package com.stock.portfolio.core.ports.incoming;

import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;

import java.util.List;

public interface SearchForStockEntry {
    List<StockEntryEntity> findStockEntry(String ticket);
}
