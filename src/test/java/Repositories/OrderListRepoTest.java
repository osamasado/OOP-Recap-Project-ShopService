package Repositories;

import DTO.Order;
import DTO.Product;
import Utils.OrderStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderListRepoTest {

    @Test
    void getAllOrders_shouldReturnAllAddedOrders_whenCalledWithTwoAddedOrders() {

        OrderListRepo orderListRepo = new OrderListRepo();

        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",10, 5);
        Product product2 = new Product("2", "Maus", "Wireless Maus",5, 10);

        Order order1 = new Order("1", LocalDate.now(), 1, List.of(product1, product2), 100,15, OrderStatus.IN_PROCESSING);

        Product product3 = new Product("1", "Laptop", "HP Laptop 2026",100, 1);
        Product product4 = new Product("2", "Maus", "Wireless Maus",10, 1);

        Order order2 = new Order("2", LocalDate.now(), 2, List.of(product3, product4), 110,2, OrderStatus.IN_PROCESSING);

        orderListRepo.addOrder(order1);
        orderListRepo.addOrder(order2);

        assertThat(orderListRepo.getAllOrders())
                .hasSize(2)
                .contains(order1, order2);
    }

    @Test
    void addOrder_shouldAddOrderToRepo_whenCalledWithOneAddedOrder() {
        OrderListRepo orderListRepo = new OrderListRepo();

        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",10, 5);
        Product product2 = new Product("2", "Maus", "Wireless Maus",5, 10);

        Order order = new Order("1", LocalDate.now(), 1, List.of(product1, product2), 100,15, OrderStatus.IN_PROCESSING);

        orderListRepo.addOrder(order);

        assertThat(orderListRepo.getAllOrders())
                .hasSize(1)
                .contains(order);
    }

    @Test
    void deleteOrder_shouldDeleteOrderFromRepo_whenCalledWithOneAddedAndDeletedOrder() {
        OrderListRepo orderListRepo = new OrderListRepo();

        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",10, 5);
        Product product2 = new Product("2", "Maus", "Wireless Maus",5, 10);

        Order order = new Order("1", LocalDate.now(), 1, List.of(product1, product2), 100,15, OrderStatus.IN_PROCESSING);

        orderListRepo.addOrder(order);
        orderListRepo.deleteOrder(order.id());

        assertThat(orderListRepo.getAllOrders())
                .isEmpty();

    }

    @Test
    void getOrderById_shouldReturnTheSameAddedOrder() {
        OrderListRepo orderListRepo = new OrderListRepo();

        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",10, 5);
        Product product2 = new Product("2", "Maus", "Wireless Maus",5, 10);

        Order order = new Order("1", LocalDate.now(), 1, List.of(product1, product2), 100,15, OrderStatus.IN_PROCESSING);

        orderListRepo.addOrder(order);
        Order expected = orderListRepo.getOrderById(order.id());

       assertEquals(expected,order);
    }
}