package com.tianyishen.backend.entity;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "stock_price")
public class StockPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private LocalDate date;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Long volume;

    @Column(name = "created_at")
    private LocalDate createdAt;

    // getters and setters
}