package com.stock.portfolio.core;

import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;
import com.stock.portfolio.core.mocks.DummyStockService;
import com.stock.portfolio.core.mocks.InMemoryStockDatabase;
import com.stock.portfolio.core.model.StockEntry;
import com.stock.portfolio.core.ports.StockFacade;
import com.stock.portfolio.core.ports.outgoing.StockService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StockFacadeTest {
    private InMemoryStockDatabase database = new InMemoryStockDatabase();
    private StockService service = new DummyStockService();
    private StockFacade facade = new StockFacade(database, service);


    @BeforeEach
    public void clearStockEntries() {
        database.cleanStockEntries();
    }

    @Test
    public void whenAddEntryDatabaseShouldContainThisEntry() {
        // given
        StockEntry stockEntry = new StockEntry();
        stockEntry.setPurchaseDate(LocalDate.parse("2022-01-15"));
        stockEntry.setPricePerShare(valueOf(15.5));
        stockEntry.setQuantity(10);
        stockEntry.setTicket("AMD");

        // when
        facade.addStockEntry(stockEntry);

        // then
        StockEntryEntity entry = database.getStockEntryByTicket("AMD");

        assertThat(entry.getTicket()).isEqualTo("AMD");
        assertThat(entry.getPurchasePrice()).isEqualTo(valueOf(15.5));
        assertThat(entry.getQuantity()).isEqualTo(10);
        assertThat(entry.getAddedDate()).isEqualTo("2022-01-15");
    }
    
    @Test
    public void whenSearchByTicketShouldReturnOneStock() {
        // given
        StockEntryEntity stockEntry = new StockEntryEntity();
        stockEntry.setTicket("IBM");
        stockEntry.setPurchasePrice(valueOf(10.0));
        stockEntry.setId(1L);
        stockEntry.setQuantity(10);
        database.addStockEntry(stockEntry);

        stockEntry = new StockEntryEntity();
        stockEntry.setTicket("AMD");
        stockEntry.setPurchasePrice(valueOf(10.0));
        stockEntry.setId(1L);
        stockEntry.setQuantity(10);
        database.addStockEntry(stockEntry);

        // when
        List<StockEntryEntity> stockEntryList = facade.findStockEntry("AMD");

        // then
        assertNotNull(stockEntryList);
    }

    @Test
    public void whenDeleteNonExistendShouldThrowException() {

    }

}
