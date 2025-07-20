package com.tianyishen.backend.controller;

import com.tianyishen.backend.entity.StockPrice;
import com.tianyishen.backend.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for exposing stock price data via HTTP endpoints.
 * URL prefix: /api/stocks
 */
@RestController
@RequestMapping("/api/stocks")
public class StockPriceController {

    private final StockPriceService stockPriceService;

    // Constructor injection of the service layer
    @Autowired
    public StockPriceController(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
    }

    /**
     * Endpoint: GET /api/stocks/{symbol}/prices
     *
     * Description:
     *  - When only symbol is provided: return all price records.
     *  - When start and end date are also provided: return price records in date range.
     *
     * @param symbol the stock symbol (e.g., "AAPL")
     * @param start optional start date (yyyy-MM-dd format)
     * @param end optional end date (yyyy-MM-dd format)
     * @return list of StockPrice entities
     */
    @GetMapping("/{symbol}/prices")
    public List<StockPrice> getPrices(
            @PathVariable String symbol,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        // If both start and end are provided, fetch price data within that range
        if (start != null && end != null) {
            return stockPriceService.getPricesInDateRange(symbol.toUpperCase(), start, end);
        }

        // Otherwise, return all prices for the symbol
        return stockPriceService.getAllPricesBySymbol(symbol.toUpperCase());
    }
}