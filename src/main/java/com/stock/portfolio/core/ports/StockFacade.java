package com.stock.portfolio.core.ports;

import com.stock.portfolio.core.model.AddStockEntryCommand;
import com.stock.portfolio.core.model.StockEntry;
import com.stock.portfolio.core.ports.incoming.AddStockEntry;
import com.stock.portfolio.core.ports.incoming.SearchForStockEntry;
import com.stock.portfolio.core.ports.outgoing.StockPortfolioDatabase;
import com.stock.portfolio.core.ports.outgoing.StockService;

import java.util.List;

public class StockFacade implements AddStockEntry, SearchForStockEntry {

    private StockPortfolioDatabase portfolioDatabase;
    private StockService stockService;

    public StockFacade(StockPortfolioDatabase portfolioDatabase, StockService stockService) {
        this.portfolioDatabase = portfolioDatabase;
        this.stockService = stockService;
    }

    @Override
    public Long addStockEntry(AddStockEntryCommand stockEntryCommand) {
        StockEntry stockEntry = mapToStockEntry(stockEntryCommand);
        stockEntry.setCurrentPrice(stockService.getPriceByTicket(stockEntryCommand.getTicket()));

        return portfolioDatabase.addStockEntry(stockEntry);
    }

    private StockEntry mapToStockEntry(AddStockEntryCommand stockEntryCommand) {
        StockEntry stockEntry = new StockEntry();
        stockEntry.setAddedDate(stockEntryCommand.getDateAdded());
        stockEntry.setPriceWhenAdded(stockEntryCommand.getPricePerShare());
        stockEntry.setQuantity(stockEntryCommand.getQuantity());
        stockEntry.setTicket(stockEntryCommand.getTicket());
        stockEntry.setCurrentPrice(stockService.getPriceByTicket(stockEntryCommand.getTicket()));

        return stockEntry;
    }

    @Override
    public List<StockEntry> findStockEntry(String ticket) {
       return portfolioDatabase.searchForStockEntry(ticket);
    }
}
