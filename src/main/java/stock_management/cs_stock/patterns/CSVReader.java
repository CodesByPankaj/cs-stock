package stock_management.cs_stock.patterns;

import org.springframework.stereotype.Service;
import stock_management.cs_stock.model.Stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class CSVReader implements ICSVReader {
    @Override
    public List<Stock> readStocksFromCSV(String filePath) throws IOException {
        List<Stock> stocks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] values = line.split(",");

                Stock stock = Stock.builder()
                        .name(values[0])
                        .openPrice(Double.parseDouble(values[1]))
                        .highPrice(Double.parseDouble(values[2]))
                        .lowPrice(Double.parseDouble(values[3]))
                        .roe(Double.parseDouble(values[4]))
                        .peRatio(Double.parseDouble(values[5]))
                        .pbRatio(Double.parseDouble(values[6]))
                        .industryPeRatio(Double.parseDouble(values[7]))
                        .marketCap(Double.parseDouble(values[8]))
                        .revenue(Double.parseDouble(values[9]))
                        .profit(Double.parseDouble(values[10]))
                        .networthPerShare(Double.parseDouble(values[11]))
                        .annualGrowthPercentage(Double.parseDouble(values[12]))
                        .build();

                stocks.add(stock);
            }
        }
        return stocks;
    }
}
