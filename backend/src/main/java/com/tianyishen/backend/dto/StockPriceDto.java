package com.tianyishen.backend.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockPriceDto {
    private LocalDate date;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
}