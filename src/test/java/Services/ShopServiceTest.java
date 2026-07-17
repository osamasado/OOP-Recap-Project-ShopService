package Services;

import DTO.Order;
import DTO.Product;
import Inertfaces.OrderRepoInterface;
import Repositories.OrderMapRepo;
import Repositories.ProductRepo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void createOrder_shouldCreateOrder_whenAllProductsExist() {
        ProductRepo productRepo = new ProductRepo();
        OrderRepoInterface orderRepo = new OrderMapRepo();

        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",10, 5);
        Product product2 = new Product("2", "Maus", "Wireless Maus",5, 10);

        productRepo.addProduct(product1);
        productRepo.addProduct(product2);

        ShopService shopService = new ShopService(productRepo, orderRepo);

        shopService.createOrder(1, productRepo.getAllProducts().values());

        assertThat(orderRepo.getAllOrders())
                .hasSize(1);

        assertThat(orderRepo.getAllOrders())
                .isNotEmpty();

        Collection<Order> orders = orderRepo.getAllOrders();
        Order fisrtOrder = new ArrayList<>(orders).getFirst();

        assertThat(orderRepo.getOrderById(fisrtOrder.id()))
                .isNotNull();

        assertThat(orderRepo.getOrderById(fisrtOrder.id()).products())
                .containsExactly(product1, product2);

    }
}