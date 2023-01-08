package com.stock.portfolio.adapters.repository;

import com.stock.portfolio.adapters.repository.entity.StockEntryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockEntryRepository extends CrudRepository<StockEntryEntity, Long> {
    @Query("SELECT s FROM StockEntryEntity s WHERE s.ticket LIKE :ticket%")
    List<StockEntryEntity> findByTicket(@Param("ticket") String ticket);
}
