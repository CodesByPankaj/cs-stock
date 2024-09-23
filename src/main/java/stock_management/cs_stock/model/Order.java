package stock_management.cs_stock.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Order {
    private String stockName;
    private int quantity;
    private double price;
    private String orderType;
}
