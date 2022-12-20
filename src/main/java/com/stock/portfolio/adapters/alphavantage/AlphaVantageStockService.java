package com.stock.portfolio.adapters.alphavantage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.portfolio.adapters.alphavantage.dto.QuoteResponse;
import com.stock.portfolio.adapters.alphavantage.dto.SearchItemResult;
import com.stock.portfolio.adapters.alphavantage.dto.SearchResponse;
import com.stock.portfolio.core.model.Stock;
import com.stock.portfolio.core.ports.outgoing.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class AlphaVantageStockService implements StockService {
    private Logger logger = Logger.getLogger(AlphaVantageStockService.class.getName());

    @Value("${alphavantage.apikey}")
    private String apiKey;

    private WebClient webClient;

    @Autowired
    private ObjectMapper objectMapper;

    public AlphaVantageStockService(@Value("${alphavantage.url}") String baseURL, @Value("${alphavantage.apikey}") String apiKey) {
        webClient = WebClient.create(baseURL);
        this.apiKey = apiKey;
    }

    @Override
    public List<Stock> findStocksByTicket(String ticket) {
        SearchResponse searchResponse = webClient.get()
                .uri(uriBuilder -> query(uriBuilder)
                        .queryParam("function", "SYMBOL_SEARCH")
                        .queryParam("keywords", ticket)
                        .build()
                )
                .retrieve()
                .bodyToMono(SearchResponse.class)
                .block();

        return mapToStocks(searchResponse.getBestMatches());
    }

    private List<Stock> mapToStocks(List<SearchItemResult> bestMatches) {
        return bestMatches.stream()
                .map(this::mapToStock)
                .collect(Collectors.toList());
    }

    private Stock mapToStock(SearchItemResult searchItemResult) {
        Stock stock = new Stock();

        stock.setName(searchItemResult.getName());
        stock.setTicket(searchItemResult.getSymbol());

        return stock;
    }

    private UriBuilder query(UriBuilder uriBuilder) {
        return uriBuilder
                .path("query")
                .queryParam("apikey", apiKey);

    }

    @Override
    public BigDecimal getPriceByTicket(String ticket) {
        QuoteResponse quoteResponse = webClient.get()
                .uri(uriBuilder -> query(uriBuilder)
                        .queryParam("function", "GLOBAL_QUOTE")
                        .queryParam("symbol", ticket)
                        .build()

                )
                .retrieve()
                .bodyToMono(QuoteResponse.class)
                .block();

        return quoteResponse.getGlobalQuote().getPrice();
    }
}
