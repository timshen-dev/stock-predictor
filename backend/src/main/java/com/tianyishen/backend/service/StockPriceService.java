package com.tianyishen.backend.service;

import com.tianyishen.backend.dto.StockPriceDto;
import com.tianyishen.backend.entity.StockPrice;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface StockPriceService {

    List<StockPriceDto> getPrices(String symbol, LocalDate start, LocalDate end, int page, int size);
    Page<StockPriceDto> getPagedPricesDto(String symbol, LocalDate start, LocalDate end, int page, int size);
}