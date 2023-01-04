package com.stock.portfolio.core.mocks;

import com.stock.portfolio.core.model.Broker;
import com.stock.portfolio.core.model.StockEntry;
import com.stock.portfolio.core.ports.outgoing.StockPortfolioDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryStockDatabase implements StockPortfolioDatabase {
    private long id = 0;

    private List<StockEntry> stockEntries = new ArrayList<>();
    private List<Broker> brokers = new ArrayList<>();

    public void cleanStockEntries() {
        stockEntries.clear();
    }

    public StockEntry getStockEntryByTicket(String ticket) {
        return stockEntries.stream()
                .filter(entry -> entry.getTicket().equals(ticket))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public Long addStockEntry(StockEntry entry) {
        stockEntries.add(entry);
        entry.setId(++id);

        return id;
    }

    @Override
    public List<StockEntry> findStockEntry(String ticket) {
        return stockEntries.stream()
                .filter(e -> e.getTicket().startsWith(ticket))
                .collect(Collectors.toList());
    }

    @Override
    public Broker getBroker(Long brokerId) {
        return brokers.stream()
                .filter(b -> b.equals(brokerId))
                .findFirst()
                .orElseThrow();
    }

    public List<StockEntry> getStockEntries() {
        return stockEntries;
    }

    public void setStockEntries(List<StockEntry> stockEntries) {
        this.stockEntries = stockEntries;
    }

    public List<Broker> getBrokers() {
        return brokers;
    }

    public void setBrokers(List<Broker> brokers) {
        this.brokers = brokers;
    }
}
