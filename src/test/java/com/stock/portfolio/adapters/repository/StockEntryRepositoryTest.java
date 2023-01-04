package com.stock.portfolio.adapters.repository;

import com.stock.portfolio.core.model.StockEntry;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StockEntryRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StockEntryRepository repository;

    @Test
    void whenFindByTicketShouldReturnStockEntry() {
        // given
        StockEntry stockEntry = new StockEntry();
        stockEntry.setTicket("TST1");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);
        entityManager.flush();

        // when
        List<StockEntry> foundResults = repository.findByTicket("TST1");

        // then
        assertThat(foundResults).hasSize(1);
    }

    @Test
    void whenFindByNotFullTicketNameShouldReturnMatchingEntries() {
        // given
        StockEntry stockEntry = new StockEntry();
        stockEntry.setTicket("TST1");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);

        stockEntry = new StockEntry();
        stockEntry.setTicket("TST2");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);

        stockEntry = new StockEntry();
        stockEntry.setTicket("SOME_OTHER");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);

        entityManager.flush();

        // when
        List<StockEntry> foundResults = repository.findByTicket("TST%");

        // then
        assertThat(foundResults).hasSize(2);
    }
}
