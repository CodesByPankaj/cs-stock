package stock_management.cs_stock.service;

import stock_management.cs_stock.model.Order;

import java.util.List;

public interface IOrderService {
    void placeOrder(Order order);
    List<Order> getOrderHistory();
    void shutdown();
}
