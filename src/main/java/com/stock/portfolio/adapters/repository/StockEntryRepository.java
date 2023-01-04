package com.stock.portfolio.adapters.repository;

import com.stock.portfolio.core.model.StockEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockEntryRepository extends CrudRepository<StockEntry, Long> {
    @Query("SELECT s FROM StockEntry s WHERE s.ticket LIKE :ticket%")
    List<StockEntry> findByTicket(@Param("ticket") String ticket);
}
