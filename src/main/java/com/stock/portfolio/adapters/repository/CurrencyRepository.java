package com.stock.portfolio.adapters.repository;

import com.stock.portfolio.adapters.repository.entity.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {
}
