package stock_management.cs_stock.constant;

public class ConstantValue {

    public static String TABLE_NAME =  "STOCK";
    public static String SELECT_QUERY =  "SELECT * FROM STOCK";
    public static String INSERT_QUERY =  "SELECT COUNT(*) FROM STOCK WHERE NAME = ?";

    public static String INSERT_TABLE_QUERY =  "INSERT INTO STOCK (NAME, OPEN_PRICE, HIGH_PRICE, LOW_PRICE, ROE, PE_RATIO, " +
            "PB_RATIO, INDUSTRY_PE_RATIO, MARKET_CAP, REVENUE, PROFIT, NETWORTH_PER_SHARE) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String CREATE_TABLE =  "CREATE TABLE STOCK (" +
            "ID SERIAL PRIMARY KEY, " +
            "NAME VARCHAR(100) UNIQUE, " +
            "OPEN_PRICE DOUBLE, " +
            "HIGH_PRICE DOUBLE, " +
            "LOW_PRICE DOUBLE, " +
            "ROE DOUBLE, " +
            "PE_RATIO DOUBLE, " +
            "PB_RATIO DOUBLE, " +
            "INDUSTRY_PE_RATIO DOUBLE, " +
            "MARKET_CAP DOUBLE, " +
            "REVENUE DOUBLE, " +
            "PROFIT DOUBLE, " +
            "NETWORTH_PER_SHARE DOUBLE)";
}
