package com.example.demo.service;

import com.example.demo.dto.StockDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TseScrapperService {

    private final
    StockBasicInfoService stockBasicInfoService;
    StaleElementReferenceException staleElementReferenceException;
    @Value("${stocks.all.url}")
    private String allStockUrl;

    @Autowired
    TseScrapperService(StockBasicInfoService stockBasicInfoService) {
        this.stockBasicInfoService = stockBasicInfoService;
    }

    public void jsoupGetAllStock() throws IOException {
        WebDriver driver = new FirefoxDriver();
        driver.get(allStockUrl);
        Document document = Jsoup.parse(driver.getPageSource());
        Element mainElement = document.getElementById("main");
        Elements allStockInfoList = mainElement.getElementsByClass("{c}");

        for (Element stockInfo : allStockInfoList) {
            Elements stockInfoElement = stockInfo.children();
            String id = stockInfo.id();

            stockBasicInfoService.saveStock(StockDto.builder().id(id)
                    .name(stockInfoElement.get(0).text())
                    .fullName(stockInfoElement.get(1).text())
                    .numberOfTransaction(stockInfoElement.get(2).text())
                    .volume(stockInfoElement.get(3).text())
                    .value(stockInfoElement.get(4).text())
                    .yesterdayPrice(stockInfoElement.get(6).text())
                    .openPrice(stockInfoElement.get(7).text())
                    .closePrice(stockInfoElement.get(8).text())
                    .lastTransactionPrice(stockInfoElement.get(9).text())
                    .lastTransactionChangePrice(stockInfoElement.get(10).text())
                    .lastTransactionPercent(stockInfoElement.get(11).text())
                    .endTransactionPrice(stockInfoElement.get(12).text())
                    .endTransactionChangePrice(stockInfoElement.get(13).text())
                    .endTransactionPercent(stockInfoElement.get(14).text())
                    .minPrice(stockInfoElement.get(15).text())
                    .maxPrice(stockInfoElement.get(16).text())
                    .eps(stockInfoElement.get(17).text())
                    .pe(stockInfoElement.get(18).text())
                    .build());
        }
    }


    public List<StockDto> getAllStock() throws IOException {
        WebDriver driver = new FirefoxDriver();
        driver.get(allStockUrl);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement mainElement = driver.findElement(By.id("main"));
        List<WebElement> webElementList = mainElement.findElements(By.xpath("//div[@class='{c}']"));
        List<StockDto> stockDtoList = new ArrayList<>();

        for (WebElement element : webElementList) {
            List<WebElement> infoElementList = new ArrayList<>();

            String id = "";
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("div")));
            infoElementList = element.findElements(By.tagName("div"));
            id = element.getAttribute("id");

            stockBasicInfoService.saveStock(StockDto.builder().id(id)
                    .name(infoElementList.get(0).getText())
                    .fullName(infoElementList.get(1).getText())
                    .numberOfTransaction(infoElementList.get(2).getText())
                    .volume(infoElementList.get(3).getText())
                    .value(infoElementList.get(4).getText())
                    .yesterdayPrice(infoElementList.get(6).getText())
                    .openPrice(infoElementList.get(7).getText())
                    .closePrice(infoElementList.get(8).getText())
                    .lastTransactionPrice(infoElementList.get(9).getText())
                    .lastTransactionChangePrice(infoElementList.get(10).getText())
                    .lastTransactionPercent(infoElementList.get(11).getText())
                    .endTransactionPrice(infoElementList.get(12).getText())
                    .endTransactionChangePrice(infoElementList.get(13).getText())
                    .endTransactionPercent(infoElementList.get(14).getText())
                    .minPrice(infoElementList.get(15).getText())
                    .maxPrice(infoElementList.get(16).getText())
                    .eps(infoElementList.get(17).getText())
                    .pe(infoElementList.get(18).getText())
                    .build());

        }
        return null;

    }
}
