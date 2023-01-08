package com.stock.portfolio.core.ports.incoming;

import com.stock.portfolio.core.model.Broker;

public interface AddBroker {
    Long addBroker(Broker addBrokerCommand);
}
