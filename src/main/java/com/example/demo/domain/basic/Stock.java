package com.example.demo.domain.basic;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Stocks")
public class Stock {
    @Id
    private String id;
    private String name;
    private String fullName;
    private String numberOfTransaction;
    private String volume;
    private String value;
    private String yesterdayPrice;

    private String openPrice;
    private String closePrice;

    private String lastTransactionPrice;
    private String lastTransactionChangePrice;
    private String lastTransactionPercent;

    private String endTransactionPrice;
    private String endTransactionChangePrice;
    private String endTransactionPercent;

    private String minPrice;
    private String maxPrice;

    private String eps;
    private String pe;

    private String buyNumber;
    private String buyVolume;
    private String buyPrice;

    private String sellNumber;
    private String sellVolume;
    private String sellPrice;
}
