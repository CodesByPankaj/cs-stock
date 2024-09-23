package stock_management.cs_stock;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stock_management.cs_stock.dao.impl.StockDAOImpl;

import java.sql.SQLException;

@SpringBootApplication
public class CsStockApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsStockApplication.class, args);

	}
}

