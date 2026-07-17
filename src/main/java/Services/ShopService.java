package Services;

import DTO.Order;
import DTO.Product;
import Inertfaces.OrderRepoInterface;
import Repositories.OrderListRepo;
import Repositories.ProductRepo;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ShopService {
    private final ProductRepo productRepo;

    private final OrderRepoInterface orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepoInterface orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public void createOrder(int customerId,Collection<Product> products) {
        List<Product> productsToOrder = new ArrayList<>();
        double totalPrice = 0.0;
        double totalAmount = 0.0;
        for (Product product: products) {
            if(productRepo.getProductById(product.id()) == null) {
                System.out.println("DTO.Product with ID: " + product.id() + " is not found in store!");
                continue;
            }
            productsToOrder.add(product);
            totalAmount += product.amount();
            totalPrice += product.amount() * product.price();
        }
        Order order = new Order(UUID.randomUUID().toString(), LocalDate.now(), customerId, productsToOrder, totalPrice, totalAmount);
        orderRepo.addOrder(order);
        System.out.println("Your order is created.");
    }

    public Collection<Order> getAllOrders() {
        return orderRepo.getAllOrders();
    }

}
