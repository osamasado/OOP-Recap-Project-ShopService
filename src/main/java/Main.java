import DTO.Product;
import Repositories.OrderListRepo;
import Repositories.ProductRepo;
import Services.ShopService;
import Utils.ConsoleColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() {

        ProductRepo productRepo = new ProductRepo();
        OrderListRepo orderRepo = new OrderListRepo();

        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",999.99, 0);
        Product product2 = new Product("2", "Maus", "Wireless Maus",29.99, 0);
        Product product3 = new Product("3", "Keyboard", "Wireless Keyboard",59.00, 0);

        List<Product> storeProducts = List.of(product1, product2);
        List<Product> orderProducts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (Product product : storeProducts) {
            System.out.print(ConsoleColors.BLUE + "Enter price of product " + product.name() + " : " + ConsoleColors.RESET);
            double price = scanner.nextDouble();
            Product productWithPrice = product.withPrice(price);
            System.out.print(ConsoleColors.YELLOW + "Enter amount of product " + product.name() + " : " + ConsoleColors.RESET);
            double amount = scanner.nextDouble();
            Product productFull = productWithPrice.withAmount(amount);
            productRepo.addProduct(productFull);
            orderProducts.add(productFull);
        }

        ShopService shopService = new ShopService(productRepo, orderRepo);

        shopService.createOrder(1, orderProducts);

//        shopService.createOrder(2, List.of(product1, product3));

        System.out.println(ConsoleColors.BLUE +shopService.getAllOrders() + ConsoleColors.RESET);
    }
}
