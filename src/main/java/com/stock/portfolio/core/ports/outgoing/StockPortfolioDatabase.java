package com.stock.portfolio.core.ports.outgoing;

import com.stock.portfolio.adapters.repository.entity.BrokerEntity;
import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;

import java.util.List;

public interface StockPortfolioDatabase {
    Long addStockEntry(StockEntryEntity entry);
    List<StockEntryEntity> findStockEntry(String ticket);
    Long addBroker(BrokerEntity broker);
    BrokerEntity getBroker(Long brokerId);

}
