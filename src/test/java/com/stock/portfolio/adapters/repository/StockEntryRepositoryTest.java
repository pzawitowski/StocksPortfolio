package com.stock.portfolio.adapters.repository;

import com.stock.portfolio.adapters.repository.entity.BrokerEntity;
import com.stock.portfolio.adapters.repository.entity.CurrencyEntity;
import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
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
        StockEntryEntity stockEntry = new StockEntryEntity();
        stockEntry.setTicket("TST1");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);
        entityManager.flush();

        // when
        List<StockEntryEntity> foundResults = repository.findByTicket("TST1");

        // then
        assertThat(foundResults).hasSize(1);
    }

    @Test
    void whenFindByNotFullTicketNameShouldReturnMatchingEntries() {
        // given
        StockEntryEntity stockEntry = new StockEntryEntity();
        stockEntry.setTicket("TST1");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);

        stockEntry = new StockEntryEntity();
        stockEntry.setTicket("TST2");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);

        stockEntry = new StockEntryEntity();
        stockEntry.setTicket("SOME_OTHER");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));

        entityManager.persist(stockEntry);

        entityManager.flush();

        // when
        List<StockEntryEntity> foundResults = repository.findByTicket("TST%");

        // then
        assertThat(foundResults).hasSize(2);
    }

    @Test
    void whenSavedShouldBePersistedWithBrokerAndCurrency() {
        // given
        BrokerEntity broker = new BrokerEntity();
        broker.setName("Test Broker");
        entityManager.persist(broker);

        CurrencyEntity currency =  new CurrencyEntity();
        currency.setCode("PLN");
        currency.setName("Polish Zloty");
        currency.setDollarValue(BigDecimal.valueOf(0.22745));

        entityManager.persist(currency);
        entityManager.flush();


        StockEntryEntity stockEntry = new StockEntryEntity();
        stockEntry.setTicket("TST1");
        stockEntry.setAddedDate(LocalDate.parse("2022-12-30"));
        stockEntry.setPurchasePrice(BigDecimal.valueOf(12.23));
        stockEntry.setBroker(broker);
        stockEntry.setCurrency(currency);

        // when
        stockEntry = repository.save(stockEntry);
        entityManager.flush();

        // then
        entityManager.detach(stockEntry);

        StockEntryEntity retrievedStockEntry = entityManager.find(StockEntryEntity.class, stockEntry.getId());

        assertThat(retrievedStockEntry.getBroker().getName()).isEqualTo("Test Broker");
        assertThat(retrievedStockEntry.getCurrency().getCode()).isEqualTo("PLN");
        assertThat(retrievedStockEntry.getCurrency().getName()).isEqualTo("Polish Zloty");
    }
}
