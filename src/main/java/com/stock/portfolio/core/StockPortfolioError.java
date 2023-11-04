package com.stock.portfolio.core;

public enum StockPortfolioError implements ErrorMessage {
    STOCK_ENTRY_NOT_FOUND("Stock entry with provided id {} not found");

    private String message;


    StockPortfolioError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
