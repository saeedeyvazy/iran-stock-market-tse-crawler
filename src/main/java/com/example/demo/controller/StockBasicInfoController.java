package com.example.demo.controller;

import com.example.demo.service.TseScrapperService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/stocks")
public class StockBasicInfoController {
    final TseScrapperService tseScrapperService;

    public StockBasicInfoController(TseScrapperService tseScrapperService) {
        this.tseScrapperService = tseScrapperService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public void listAuthors() throws IOException {
        tseScrapperService.jsoupGetAllStock();
    }
}
