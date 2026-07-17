import DTO.Product;
import Repositories.OrderListRepo;
import Repositories.ProductRepo;
import Services.ShopService;
import Utils.ConsoleColors;
import Utils.ProductCsvReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() {

        ProductRepo productRepo = new ProductRepo();
        OrderListRepo orderRepo = new OrderListRepo();
        ProductCsvReader reader = new ProductCsvReader();

        reader.loadProducts(productRepo);

        String line = "-".repeat(200);
        System.out.printf("%-15s %-50s %-50s %-10s %-10s", "id|", "Name", "description", "Price", "Amount");
        System.out.println();
        System.out.println(line);
        char separator = '|';
        for(Product product: productRepo.getAllProducts().values()) {
            System.out.printf("%-15s %-1s %-50s %-50s %-10s %-10s", product.id(), separator, product.name(), product.description(), product.price(), product.amount());
            System.out.println();
        }
        Scanner scanner = new Scanner(System.in);

        boolean orderIsFinished = false;
        List<Product> orderProducts = new ArrayList<>();
        while (!orderIsFinished) {
            System.out.print(
                    ConsoleColors.GREEN +
                            "Enter your product id (type 'ESC' to finish): "
                            + ConsoleColors.RESET
            );
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("ESC")) {
                if(orderProducts.isEmpty()) {
                    System.out.println(ConsoleColors.RED + "Cancelled!" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + "Congratulation! Yor order is sent" + ConsoleColors.RESET );
                }
                orderIsFinished = true;
            } else {
                Product product = productRepo.getProductById(input);
                if(product == null) {
                    System.out.println(ConsoleColors.RED + "Sorry! Your chosen product is not foud in store!" + ConsoleColors.RESET);
                    continue;
                }
                System.out.print(ConsoleColors.YELLOW +
                        "Enter amount of product " + product.name() + " : "
                        + ConsoleColors.RESET);

                String amountInput = scanner.nextLine();

                if (amountInput.equalsIgnoreCase("ESC")) {
                    System.out.println(ConsoleColors.RED + "Cancelled!" + ConsoleColors.RESET);
                    continue;
                }
                double amount = Double.parseDouble(amountInput);
                Product productFull = product.withAmount(amount);
                System.out.println("Product: " + productFull);
                orderProducts.add(productFull);
                System.out.println(ConsoleColors.GREEN + "Your product is added to you order successfully!");
            }
        }

//        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",999.99, 0);
//        Product product2 = new Product("2", "Maus", "Wireless Maus",29.99, 0);
//        Product product3 = new Product("3", "Keyboard", "Wireless Keyboard",59.00, 0);
//
//        List<Product> storeProducts = List.of(product1, product2);
//
//        for (Product product : storeProducts) {
//            System.out.print(ConsoleColors.BLUE + "Enter price of product " + product.name() + " : " + ConsoleColors.RESET);
//            double price = scanner.nextDouble();
//            Product productWithPrice = product.withPrice(price);
//            System.out.print(ConsoleColors.YELLOW + "Enter amount of product " + product.name() + " : " + ConsoleColors.RESET);
//            double amount = scanner.nextDouble();
//            Product productFull = productWithPrice.withAmount(amount);
//            productRepo.addProduct(productFull);
//            orderProducts.add(productFull);
//        }

        ShopService shopService = new ShopService(productRepo, orderRepo);

        shopService.createOrder(1, orderProducts);

//        shopService.createOrder(2, List.of(product1, product3));

        System.out.println(ConsoleColors.BLUE +shopService.getAllOrders() + ConsoleColors.RESET);
    }
}
