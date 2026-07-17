package Repositories;

import DTO.Order;
import Inertfaces.OrderRepoInterface;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderMapRepo implements OrderRepoInterface {
    private final Map<String, Order> orders = new HashMap<>();

    @Override
    public void addOrder(Order order) {
        orders.put(order.id(), order);
    }

    @Override
    public void deleteOrder(String orderId) {
        orders.remove(orderId);
    }

    @Override
    public Collection<Order> getAllOrders() {
        return orders.values();
    }

    @Override
    public Order getOrderById(String orderId) {
        return orders.get(orderId);
    }
}
