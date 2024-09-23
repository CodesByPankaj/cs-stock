package stock_management.cs_stock.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class Stock {
    private String name;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double roe;
    private double peRatio;
    private double pbRatio;
    private double industryPeRatio;
    private double marketCap;
    private double revenue;
    private double profit;
    private double networthPerShare;
    private double annualGrowthPercentage;

    public Stock(String name, double openPrice) {
        this.name = name;
        this.openPrice = openPrice;
    }
}

