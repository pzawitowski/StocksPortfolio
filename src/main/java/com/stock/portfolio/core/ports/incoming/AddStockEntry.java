package com.stock.portfolio.core.ports.incoming;

import com.stock.portfolio.core.model.AddStockEntryCommand;

public interface AddStockEntry {
    Long addStockEntry(AddStockEntryCommand stockEntryCommand);
}
