package stock_management.cs_stock;

import org.junit.jupiter.api.Test;
import stock_management.cs_stock.interpreter.Dashboard;
import stock_management.cs_stock.model.Stock;
import stock_management.cs_stock.service.IStockService;
import stock_management.cs_stock.service.impl.StockService;

import java.util.Arrays;
import java.util.List;

public class DashboardTests {

    @Test
    public void testDisplayRecommendedStocks() {
        IStockService stockService = new StockService();
        Dashboard dashboard = new Dashboard(stockService);

        List<Stock> recommendedStocks = Arrays.asList(
                new Stock("TCS", 150.00),
        new Stock("RELIANCE", 2800.00)
        );

        dashboard.displayRecommendedStocks(recommendedStocks);
    }

    @Test
    public void testGenerateReport() {
        IStockService stockService = new StockService();
        Dashboard dashboard = new Dashboard(stockService);

        List<Stock> recommendedStocks = Arrays.asList(
                new Stock("RELIANCE", 150.00),
                new Stock("HDFCBANK", 2800.00)
        );

        dashboard.generateReport(recommendedStocks);
    }

}

