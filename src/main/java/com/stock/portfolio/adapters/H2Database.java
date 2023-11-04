package com.stock.portfolio.adapters;

import com.stock.portfolio.adapters.repository.StockEntryRepository;
import com.stock.portfolio.adapters.repository.entity.BrokerEntity;
import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;
import com.stock.portfolio.core.ports.outgoing.StockPortfolioDatabase;

import java.util.List;

public class H2Database implements StockPortfolioDatabase {
    private StockEntryRepository stockEntryRepository;

    public H2Database(StockEntryRepository stockEntryRepository) {
        this.stockEntryRepository = stockEntryRepository;
    }

    @Override
    public Long addStockEntry(StockEntryEntity entry) {
        return stockEntryRepository.save(entry).getId();
    }

    @Override
    public List<StockEntryEntity> findStockEntry(String ticket) {
        return stockEntryRepository.findByTicket(ticket);
    }

    @Override
    public Long addBroker(BrokerEntity broker) {
        return null;
    }

    @Override
    public BrokerEntity getBroker(Long brokerId) {
        return null;
    }

    @Override
    public void deleteStockEntry(Long stockEntryId) {
        stockEntryRepository.deleteById(stockEntryId);
    }
}
