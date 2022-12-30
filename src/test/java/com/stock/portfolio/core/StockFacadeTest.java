package com.stock.portfolio.core;

import com.stock.portfolio.core.mocks.DummyStockService;
import com.stock.portfolio.core.mocks.InMemoryStockDatabase;
import com.stock.portfolio.core.model.AddStockEntryCommand;
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
    public void whenAddEntryCommandDatabaseShouldContainThisEntry() {
        // given
        AddStockEntryCommand addEntryCommand = new AddStockEntryCommand();
        addEntryCommand.setDateAdded(LocalDate.parse("2022-01-15"));
        addEntryCommand.setPricePerShare(valueOf(15.5));
        addEntryCommand.setQuantity(10);
        addEntryCommand.setTicket("AMD");

        // when
        facade.addStockEntry(addEntryCommand);

        // then
        StockEntry entry = database.getStockEntryByTicket("AMD");

        assertThat(entry.getTicket()).isEqualTo("AMD");
        assertThat(entry.getPriceWhenAdded()).isEqualTo(valueOf(15.5));
        assertThat(entry.getQuantity()).isEqualTo(10);
        assertThat(entry.getAddedDate()).isEqualTo("2022-01-15");
    }

    




    @Test
    public void whenSearchByTicketShouldReturnOneStock() {
        // given
        StockEntry stockEntry = new StockEntry();
        stockEntry.setTicket("IBM");
        stockEntry.setPriceWhenAdded(valueOf(10.0));
        stockEntry.setId(1L);
        stockEntry.setQuantity(10);
        database.addStockEntry(stockEntry);

        stockEntry = new StockEntry();
        stockEntry.setTicket("AMD");
        stockEntry.setPriceWhenAdded(valueOf(10.0));
        stockEntry.setId(1L);
        stockEntry.setQuantity(10);
        database.addStockEntry(stockEntry);

        // when
        List<StockEntry> stockEntryList = facade.findStockEntry("AMD");

        // then
        assertNotNull(stockEntryList);
    }

}
