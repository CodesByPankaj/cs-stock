package stock_management.cs_stock.patterns;

public class CSVReaderSingleton {
    private static CSVReaderSingleton instance;
    private ICSVReader csvReader;

    private CSVReaderSingleton(ICSVReader csvReader) {
        this.csvReader = csvReader;
    }

    public static CSVReaderSingleton getInstance(ICSVReader csvReader) {
        if (instance == null) {
            instance = new CSVReaderSingleton(csvReader);
        }
        return instance;
    }
}
