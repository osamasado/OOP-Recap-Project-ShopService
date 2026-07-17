package Repositories;

import DTO.Order;
import Inertfaces.OrderRepoInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OrderListRepo implements OrderRepoInterface {
    private final List<Order> orders = new ArrayList<>();

    public Collection<Order> getAllOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deleteOrder(String orderId) {
        orders.removeIf(order -> order.id().equals(orderId));
    }

    public Order getOrderById(String orderId) {
        for (Order order : orders) {
            if (order.id().equals(orderId)) {
                return order;
            }
        }
       return null;
    }
}
