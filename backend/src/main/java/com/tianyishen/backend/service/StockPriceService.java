package com.tianyishen.backend.service;

import com.tianyishen.backend.entity.StockPrice;

import java.time.LocalDate;
import java.util.List;

public interface StockPriceService {

    List<StockPrice> getAllPricesBySymbol(String symbol);

    List<StockPrice> getPricesInDateRange(String symbol, LocalDate start, LocalDate end);
}