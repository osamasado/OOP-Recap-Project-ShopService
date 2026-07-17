package Inertfaces;

import DTO.Order;

import java.util.Collection;

public interface OrderRepoInterface {

    public void addOrder(Order order);

    public void deleteOrder(String orderId);

    public Collection<Order> getAllOrders();

    public Order getOrderById(String orderId);
}
