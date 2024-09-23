package stock_management.cs_stock.dao;

import stock_management.cs_stock.model.Stock;

import java.sql.SQLException;
import java.util.List;

public interface IStockDAO {
    void createTable() throws SQLException;
    void insertStock(Stock stock) throws SQLException;
    List<Stock> getAllStocks() throws SQLException;
}

