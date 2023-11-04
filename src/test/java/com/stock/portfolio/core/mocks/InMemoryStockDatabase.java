package com.stock.portfolio.core.mocks;

import com.stock.portfolio.adapters.repository.entity.BrokerEntity;
import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;
import com.stock.portfolio.core.PortfolioException;
import com.stock.portfolio.core.StockPortfolioError;
import com.stock.portfolio.core.ports.outgoing.StockPortfolioDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryStockDatabase implements StockPortfolioDatabase {
    private AtomicLong id = new AtomicLong(0);

    private List<StockEntryEntity> stockEntries = new ArrayList<>();
    private List<BrokerEntity> brokers = new ArrayList<>();

    public void cleanStockEntries() {
        stockEntries.clear();
    }

    public StockEntryEntity getStockEntryByTicket(String ticket) {
        return stockEntries.stream()
                .filter(entry -> entry.getTicket().equals(ticket))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Long addStockEntry(StockEntryEntity entry) {
        stockEntries.add(entry);
        entry.setId(id.incrementAndGet());

        return entry.getId();
    }

    @Override
    public List<StockEntryEntity> findStockEntry(String ticket) {
        return stockEntries.stream()
                .filter(e -> e.getTicket().startsWith(ticket))
                .collect(Collectors.toList());
    }

    @Override
    public Long addBroker(BrokerEntity broker) {
        broker.setId(id.incrementAndGet());
        brokers.add(broker);
        return broker.getId();
    }


    @Override
    public BrokerEntity getBroker(Long brokerId) {
        return brokers.stream()
                .filter(b -> b.equals(brokerId))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public void deleteStockEntry(Long stockEntryId) {
        if (stockEntries.contains(stockEntryId)) {
            stockEntries.remove(stockEntryId);
        } else {
          throw new PortfolioException(StockPortfolioError.STOCK_ENTRY_NOT_FOUND, stockEntryId);
        }
    }

    public List<StockEntryEntity> getStockEntries() {
        return stockEntries;
    }

    public void setStockEntries(List<StockEntryEntity> stockEntries) {
        this.stockEntries = stockEntries;
    }

    public List<BrokerEntity> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<BrokerEntity> brokers) {
        this.brokers = brokers;
    }
}
