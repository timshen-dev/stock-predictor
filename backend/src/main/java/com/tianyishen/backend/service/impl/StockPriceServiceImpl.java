package com.tianyishen.backend.service.impl;

import com.tianyishen.backend.dto.StockPriceDto;
import com.tianyishen.backend.entity.StockPrice;
import com.tianyishen.backend.repository.StockPriceRepository;
import com.tianyishen.backend.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockPriceServiceImpl implements StockPriceService {

    private final StockPriceRepository stockPriceRepository;

    @Autowired
    public StockPriceServiceImpl(StockPriceRepository stockPriceRepository) {
        this.stockPriceRepository = stockPriceRepository;
    }

    @Override
    public List<StockPriceDto> getPrices(String symbol, LocalDate start, LocalDate end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());

        Page<StockPrice> resultPage;

        if (start != null && end != null) {
            resultPage = stockPriceRepository.findBySymbolAndDateBetween(symbol, start, end, pageable);
        } else {
            resultPage = stockPriceRepository.findBySymbol(symbol, pageable);
        }

        return resultPage.getContent().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Page<StockPriceDto> getPagedPricesDto(String symbol, LocalDate start, LocalDate end, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("date").descending());

        Page<StockPrice> resultPage;
        if (start != null && end != null) {
            resultPage = stockPriceRepository.findBySymbolAndDateBetween(symbol, start, end, pageable);
        } else {
            resultPage = stockPriceRepository.findBySymbol(symbol, pageable);
        }

        return resultPage.map(this::toDto); // 自动 Page<Entity> → Page<DTO>
    }


    private StockPriceDto toDto(StockPrice stock) {
        StockPriceDto dto = new StockPriceDto();
        dto.setDate(stock.getDate());
        dto.setOpen(stock.getOpen());
        dto.setClose(stock.getClose());
        dto.setHigh(stock.getHigh());
        dto.setLow(stock.getLow());
        return dto;
    }
}