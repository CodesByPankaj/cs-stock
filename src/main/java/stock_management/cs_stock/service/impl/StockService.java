package stock_management.cs_stock.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stock_management.cs_stock.model.Stock;
import stock_management.cs_stock.service.IStockService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    @Override
    public List<Stock> getTopThreeStocksByPrice(List<Stock> stocks) {
        return stocks.stream()
                .sorted(Comparator.comparingDouble(Stock::getHighPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    @Override
    public Stock getHighestStock(List<Stock> stocks) {
        return stocks.stream()
                .max(Comparator.comparingDouble(Stock::getHighPrice))
                .orElse(null);
    }

    @Override
    public Stock getLowestStock(List<Stock> stocks) {
        return stocks.stream()
                .min(Comparator.comparingDouble(Stock::getLowPrice))
                .orElse(null);
    }

    @Override
    public List<Stock> recommendStocks(List<Stock> stocks, double maxPeRatio) {
        return stocks.stream()
                .filter(stock -> stock.getPeRatio() < maxPeRatio)
                .sorted(Comparator.comparingDouble(Stock::getPeRatio))
                .collect(Collectors.toList());
    }
}

