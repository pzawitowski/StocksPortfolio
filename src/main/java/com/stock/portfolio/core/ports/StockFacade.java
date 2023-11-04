package com.stock.portfolio.core.ports;

import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;
import com.stock.portfolio.core.model.Broker;
import com.stock.portfolio.core.model.StockEntry;
import com.stock.portfolio.core.ports.incoming.AddBroker;
import com.stock.portfolio.core.ports.incoming.AddStockEntry;
import com.stock.portfolio.core.ports.incoming.DeleteStockEntry;
import com.stock.portfolio.core.ports.incoming.SearchForStockEntry;
import com.stock.portfolio.core.ports.outgoing.StockPortfolioDatabase;
import com.stock.portfolio.core.ports.outgoing.StockService;

import java.util.List;

public class StockFacade implements AddStockEntry, SearchForStockEntry, AddBroker, DeleteStockEntry {

    private StockPortfolioDatabase portfolioDatabase;
    private StockService stockService;

    public StockFacade(StockPortfolioDatabase portfolioDatabase, StockService stockService) {
        this.portfolioDatabase = portfolioDatabase;
        this.stockService = stockService;
    }

    @Override
    public Long addStockEntry(StockEntry stockEntry) {
        StockEntryEntity stockEntryEntity = mapToStockEntryEntity(stockEntry);
        stockEntryEntity.setCurrentPrice(stockService.getPriceByTicket(stockEntryEntity.getTicket()));

        return portfolioDatabase.addStockEntry(stockEntryEntity);
    }

    private StockEntryEntity mapToStockEntryEntity(StockEntry stockEntry) {
        StockEntryEntity stockEntryEntity = new StockEntryEntity();
        stockEntryEntity.setAddedDate(stockEntry.getPurchaseDate());
        stockEntryEntity.setPurchasePrice(stockEntry.getPricePerShare());
        stockEntryEntity.setQuantity(stockEntry.getQuantity());
        stockEntryEntity.setTicket(stockEntry.getTicket());
        stockEntryEntity.setCurrentPrice(stockService.getPriceByTicket(stockEntry.getTicket()));

        return stockEntryEntity;
    }

    @Override
    public List<StockEntryEntity> findStockEntry(String ticket) {
       return portfolioDatabase.findStockEntry(ticket);
    }

    @Override
    public Long addBroker(Broker addBrokerCommand) {
        return null;
    }

    @Override
    public void deleteStockEntry(Long stockEntryId) {
        portfolioDatabase.deleteStockEntry(stockEntryId);
    }

}
