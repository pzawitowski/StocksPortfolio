package com.stock.portfolio.adapters.alphavantage.dto;

import java.util.List;

public class SearchResponse {
    private List<SearchItemResult> bestMatches;

    public List<SearchItemResult> getBestMatches() {
        return bestMatches;
    }

    public void setBestMatches(List<SearchItemResult> bestMatches) {
        this.bestMatches = bestMatches;
    }
}
