package com.example.demo.service;

import com.example.demo.common.ObjectMapperUtils;
import com.example.demo.domain.basic.Stock;
import com.example.demo.dto.StockDto;
import com.example.demo.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockBasicInfoService {
    private final StockRepository stockRepository;

    @Autowired
    StockBasicInfoService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public void saveAllStock(List<StockDto> stockDtoList) {
        List<Stock> stockList = new ArrayList<>();
        stockList = ObjectMapperUtils.mapAll(stockDtoList, Stock.class);

        stockRepository.saveAll(stockList);
    }

    public void saveStock(StockDto stockDto) {
        stockRepository.save(ObjectMapperUtils.map(stockDto, Stock.class));
    }
}
