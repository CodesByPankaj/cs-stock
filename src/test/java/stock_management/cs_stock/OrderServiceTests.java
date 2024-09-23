package stock_management.cs_stock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stock_management.cs_stock.model.Order;
import stock_management.cs_stock.service.IOrderService;
import stock_management.cs_stock.service.impl.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class OrderServiceTests {
    private IOrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testPlaceOrder() throws InterruptedException {
        Order order = Order.builder()
                .stockName("HDFCBANK")
                .quantity(10)
                .price(150.0)
                .orderType("BUY")
                .build();

        orderService.placeOrder(order);
        log.info("Order placed: {}", order);


        Thread.sleep(100);

        List<Order> orderHistory = orderService.getOrderHistory();
        assertFalse(orderHistory.isEmpty(), "Order history should not be empty");
        assertTrue(orderHistory.contains(order), "Order should be in the order history");
        log.info("Order verified in history: {}", order.getStockName().toString());
    }

    @AfterEach
    public void tearDown() {
        orderService.shutdown();
    }
}

