package stock_management.cs_stock.patterns;

import stock_management.cs_stock.model.Stock;

import java.io.IOException;
import java.util.List;

public interface ICSVReader {
    List<Stock> readStocksFromCSV(String filePath) throws IOException;

}
