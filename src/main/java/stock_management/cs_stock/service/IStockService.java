package stock_management.cs_stock.service;

import stock_management.cs_stock.model.Stock;

import java.util.List;

public interface IStockService {
        List<Stock> getTopThreeStocksByPrice(List<Stock> stocks);
        Stock getHighestStock(List<Stock> stocks);
        Stock getLowestStock(List<Stock> stocks);
        List<Stock> recommendStocks(List<Stock> stocks, double maxPeRatio);


}
