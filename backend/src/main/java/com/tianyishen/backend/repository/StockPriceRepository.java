package com.tianyishen.backend.repository;

import com.tianyishen.backend.entity.StockPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
// Long；Pk type Spring will create findAll()...
public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {

    Page<StockPrice> findBySymbol(String symbol, Pageable pageable);

    Page<StockPrice> findBySymbolAndDateBetween(String symbol, LocalDate start, LocalDate end, Pageable pageable);
}