package stock_management.cs_stock.dao.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock_management.cs_stock.constant.ConstantValue;
import stock_management.cs_stock.dao.IStockDAO;
import stock_management.cs_stock.model.Stock;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class StockDAOImpl implements IStockDAO {
    private static final String jdbcUrl = "jdbc:h2:~/s_db";
    private static final String username = "sa";
    private static final String password = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }


    public void createTable() throws SQLException {
        String createTableSQL = ConstantValue.CREATE_TABLE;

        try (Connection conn = getConnection()) {
            if (!doesTableExist(conn, ConstantValue.TABLE_NAME)) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute(createTableSQL);
                    log.info("Table 'STOCK' created successfully.");
                }
            } else {
                log.warn("Table 'STOCK' already exists, skipping creation.");
            }
        }
    }

    private boolean doesTableExist(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        try (ResultSet rs = metaData.getTables(null, null, tableName.toUpperCase(), null)) {
            return rs.next();
        }
    }


    @Override
    public void insertStock(Stock stock) throws SQLException {
        String insertData = ConstantValue.INSERT_QUERY ;
        String insertQuery = ConstantValue.INSERT_TABLE_QUERY;

        try (Connection conn = getConnection();
             PreparedStatement Stmt = conn.prepareStatement(insertData);
             PreparedStatement istmt = conn.prepareStatement(insertQuery)) {

            Stmt.setString(1, stock.getName());
            ResultSet rs = Stmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);

            if (count == 0) {
                istmt.setString(1, stock.getName());
                istmt.setDouble(2, stock.getOpenPrice());
                istmt.setDouble(3, stock.getHighPrice());
                istmt.setDouble(4, stock.getLowPrice());
                istmt.setDouble(5, stock.getRoe());
                istmt.setDouble(6, stock.getPeRatio());
                istmt.setDouble(7, stock.getPbRatio());
                istmt.setDouble(8, stock.getIndustryPeRatio());
                istmt.setDouble(9, stock.getMarketCap());
                istmt.setDouble(10, stock.getRevenue());
                istmt.setDouble(11, stock.getProfit());
                istmt.setDouble(12, stock.getNetworthPerShare());

                int i = istmt.executeUpdate();
//                log.info("Rows affected: {} " , i);
            } else {
                log.info("Stock with name : {} " , stock.getName() + " already exists. Skipping insertion.");
            }
        } catch (SQLException e) {
            log.error("Error inserting stock: {} " , e.getMessage());
        }
    }

    @Override
    public List<Stock> getAllStocks() throws SQLException {
        List<Stock> stocks = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(ConstantValue.SELECT_QUERY)) {
            while (rs.next()) {
                Stock stock = Stock.builder()
                        .name(rs.getString("NAME"))
                        .openPrice(rs.getDouble("OPEN_PRICE"))
                        .highPrice(rs.getDouble("HIGH_PRICE"))
                        .lowPrice(rs.getDouble("LOW_PRICE"))
                        .roe(rs.getDouble("ROE"))
                        .peRatio(rs.getDouble("PE_RATIO"))
                        .pbRatio(rs.getDouble("PB_RATIO"))
                        .industryPeRatio(rs.getDouble("INDUSTRY_PE_RATIO"))
                        .marketCap(rs.getDouble("MARKET_CAP"))
                        .revenue(rs.getDouble("REVENUE"))
                        .profit(rs.getDouble("PROFIT"))
                        .networthPerShare(rs.getDouble("NETWORTH_PER_SHARE"))
                        .build();
                stocks.add(stock);
//                log.info("Stocks list : {} " , stock);
            }
        }
        return stocks;
    }
}

