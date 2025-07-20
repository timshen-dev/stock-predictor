package com.tianyishen.backend.controller;

import com.tianyishen.backend.dto.StockPriceDto;
import com.tianyishen.backend.response.*;
import com.tianyishen.backend.service.StockPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockPriceController {

    private final StockPriceService stockPriceService;

    @Autowired
    public StockPriceController(StockPriceService stockPriceService) {
        this.stockPriceService = stockPriceService;
    }

    @GetMapping("/{symbol}/prices")
    public ResponseEntity<ApiResponse<PagedResponse<StockPriceDto>>> getPrices(
            @PathVariable String symbol,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<StockPriceDto> resultPage = stockPriceService.getPagedPricesDto(symbol.toUpperCase(), start, end, page, size);

        PagedResponse<StockPriceDto> pagedResponse = new PagedResponse<>(
                resultPage.getContent(),
                resultPage.getNumber(),
                resultPage.getSize(),
                resultPage.getTotalElements(),
                resultPage.getTotalPages()
        );

        return ResponseEntity.ok(ApiResponse.success(pagedResponse));
    }
}