package stock_management.cs_stock.interpreter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock_management.cs_stock.model.Stock;
import stock_management.cs_stock.service.IStockService;
import stock_management.cs_stock.util.ReportGenerator;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Dashboard {
    private final IStockService stockService;


    public void displayRecommendedStocks(List<Stock> recommendedStocks) {
        System.out.println("Recommended Stocks:");
        for (Stock stock : recommendedStocks) {
            log.info("Stock: {} , Price: {} ", stock.getName(), stock.getOpenPrice());
        }
    }

    public void generateReport(List<Stock> recommendedStocks) {
        ReportGenerator.generateReport(recommendedStocks);
    }
}

