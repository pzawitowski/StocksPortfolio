package com.stock.portfolio.adapters.repository;

import com.stock.portfolio.adapters.repository.entity.BrokerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrokerRepository extends CrudRepository<BrokerEntity, Long> {
}
