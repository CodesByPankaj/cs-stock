package stock_management.cs_stock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import stock_management.cs_stock.dao.IStockDAO;
import stock_management.cs_stock.dao.impl.StockDAOImpl;
import stock_management.cs_stock.interpreter.Dashboard;
import stock_management.cs_stock.patterns.CSVReader;
import stock_management.cs_stock.model.Order;
import stock_management.cs_stock.model.Stock;
import stock_management.cs_stock.service.IOrderService;
import stock_management.cs_stock.service.IStockService;
import stock_management.cs_stock.service.impl.OrderService;
import stock_management.cs_stock.service.impl.StockService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class StocksServiceTests {
    private IStockService stockServices;
    private IStockDAO stockDAO;
    private CSVReader csvReader;
    private IOrderService orderService;


    @BeforeEach
    public void setUp() throws SQLException, IOException {
        stockDAO = new StockDAOImpl();
        orderService = new OrderService();
        stockDAO.createTable();
        stockServices = new StockService();
        csvReader = new CSVReader();
        List<Stock> stocksFromCSV = csvReader.readStocksFromCSV("src/main/resources/stocks.csv");
        log.info("Stocks read from CSV: {} " , stocksFromCSV.size());
        for (Stock stock : stocksFromCSV) {
            stockDAO.insertStock(stock);
        }
    }

    @Test
    public void allStocks() throws SQLException {
        List<Stock> allStocks = stockDAO.getAllStocks();
        log.info("Fetch all Stocks : {} " , allStocks.stream().toList());
    }

    @Test
    public void testGetTopThreeStocksByPrice() throws SQLException {
        List<Stock> allStocks = stockDAO.getAllStocks();
        List<Stock> topThree = stockServices.getTopThreeStocksByPrice(allStocks);
        System.out.println(topThree);

        assertEquals(3, topThree.size());
//        assertEquals("TCS", topThree.get(0).getName());
//        assertEquals("HDFCBNAK", topThree.get(1).getName());
//        assertEquals("RELIENCE", topThree.get(2).getName());
    }

    @Test
    public void testGetHighestStock() throws SQLException {
        List<Stock> allStocks = stockDAO.getAllStocks();
        Stock highestStock = stockServices.getHighestStock(allStocks);
        log.info("Highest Stock: {}" , highestStock);
        assertNotNull(highestStock);
    }

    @Test
    public void testGetLowestStock() throws SQLException {
        List<Stock> allStocks = stockDAO.getAllStocks();
        Stock lowestStock = stockServices.getLowestStock(allStocks);
        log.info("Lowest Stock: {}" , lowestStock);
        assertNotNull(lowestStock);
    }

    @Test
    public void testRecommendStocks() throws SQLException {
        List<Stock> allStocks = stockDAO.getAllStocks();
        List<Stock> recommendedStocks = stockServices.recommendStocks(allStocks, 15.0);
        log.info("Recommended Stocks: {}" , recommendedStocks.stream().map(Stock::getName).collect(Collectors.toList()));
        assertFalse(recommendedStocks.isEmpty());
        for (Stock stock : recommendedStocks) {
            Order order = Order.builder()
                    .stockName(stock.getName())
                    .quantity(1)
                    .price(stock.getOpenPrice())
                    .orderType("BUY")
                    .build();

            orderService.placeOrder(order);
            log.info("Order placed for stock: {}", stock.getName());
        }
    }

    @Test
    public void testDashboardFunctionality() throws SQLException {
        List<Stock> allStocks = stockDAO.getAllStocks();
        List<Stock> recommendedStocks = stockServices.recommendStocks(allStocks, 15.0);

        Dashboard dashboard = new Dashboard(stockServices);
        dashboard.displayRecommendedStocks(recommendedStocks);
        dashboard.generateReport(recommendedStocks);
    }



}

