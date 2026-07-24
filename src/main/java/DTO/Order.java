package DTO;

import Utils.OrderStatus;

import java.time.Instant;
import java.util.List;

public record Order(String id, Instant date, int customerId, List<Product> products, double totalPrice,
                    double totalAmount,
                    OrderStatus status) {
    
}
