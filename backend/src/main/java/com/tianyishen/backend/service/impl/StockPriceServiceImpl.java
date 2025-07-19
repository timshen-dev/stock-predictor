package com.tianyishen.backend.service.impl;

import com.tianyishen.backend.entity.StockPrice;
import com.tianyishen.backend.repository.StockPriceRepository;
import com.tianyishen.backend.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    private final StockPriceRepository stockPriceRepository;

    @Autowired
    public StockPriceServiceImpl(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    @Override
    public List<StockPrice> getAllPricesBySymbol(String symbol) {
        return stockPriceRepository.findBySymbol(symbol);
    }

    @Override
    public List<StockPrice> getPricesInDateRange(String symbol, LocalDate start, LocalDate end) {
        return stockPriceRepository.findBySymbolAndDateBetween(symbol, start, end);
    }
}