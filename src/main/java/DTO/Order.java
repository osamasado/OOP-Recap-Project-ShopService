package DTO;

import Utils.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public record Order(String id, LocalDate date, int customerId, List<Product> products, double totalPrice,
                    double totalAmount,
                    OrderStatus status) {
    
}
