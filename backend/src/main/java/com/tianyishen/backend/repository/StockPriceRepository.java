package com.tianyishen.backend.repository;

import com.tianyishen.backend.entity.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    List<StockPrice> findBySymbol(String symbol);

    List<StockPrice> findBySymbolAndDateBetween(String symbol, LocalDate start, LocalDate end);
}