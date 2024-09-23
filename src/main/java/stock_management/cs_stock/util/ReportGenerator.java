package stock_management.cs_stock.util;

import lombok.extern.slf4j.Slf4j;
import stock_management.cs_stock.model.Stock;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Slf4j
public class ReportGenerator {

    public static void generateReport(List<Stock> recommendedStocks) {
        StringBuilder report = new StringBuilder();
        report.append("Stock Recommendation Report\n");
        report.append("===========================\n");
        for (Stock stock : recommendedStocks) {
            report.append(String.format("Stock: %s, Price: %.2f%n", stock.getName(), stock.getOpenPrice()));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("StockRecommendationReport.txt"))) {
            writer.write(report.toString());
           log.info("Report written to StockRecommendationReport.txt");
        } catch (IOException e) {
            log.error("Error writing report: {}", e.getMessage());
        }
    }
}

