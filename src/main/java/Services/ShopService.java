package Services;

import DTO.Order;
import DTO.Product;
import Inertfaces.OrderRepoInterface;
import Repositories.ProductRepo;
import Utils.ConsoleColors;
import Utils.OrderStatus;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.*;

@RequiredArgsConstructor
public class ShopService {
    private final ProductRepo productRepo;

    private final OrderRepoInterface orderRepo;

    public void createOrder(int customerId,Collection<Product> products) {
        List<Product> productsToOrder = new ArrayList<>();
        double totalPrice = 0.0;
        double totalAmount = 0.0;
        for (Product product: products) {
            if(productRepo.getProductById(product.id()).isEmpty()) {
                throw new NoSuchElementException("DTO.Product with ID: " + product.id() + " is not found in store!");
            }
            productsToOrder.add(product);
            totalAmount += product.amount();
            totalPrice += product.amount() * product.price();
        }
        Order order = new Order(UUID.randomUUID().toString(), Instant.now(), customerId, productsToOrder, totalPrice, totalAmount, OrderStatus.IN_PROCESSING);
        orderRepo.addOrder(order);
        System.out.println(ConsoleColors.GREEN + "Your order is created." + ConsoleColors.RESET);
    }

    public Collection<Order> getAllOrders() {
        return orderRepo.getAllOrders();
    }

    public Collection<Order> getOrdersByStatus(OrderStatus orderStatus) {
        return orderRepo.getAllOrders().stream()
                .filter(order -> order.status().equals(orderStatus))
                .toList();
    }

}
