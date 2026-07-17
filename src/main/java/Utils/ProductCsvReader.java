package Utils;

import DTO.Product;
import Repositories.ProductRepo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class ProductCsvReader {

    public void loadProducts(ProductRepo repo) {

        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     Objects.requireNonNull(getClass().getClassLoader()
                                             .getResourceAsStream("products.csv"))
                             ))) {

            String line;

            // Header überspringen
            reader.readLine();

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                Product product = new Product(
                        data[0],
                        data[1],
                        data[2],
                        Double.parseDouble(data[3]),
                        Integer.parseInt(data[4])
                );

                repo.addProduct(product);
            }

        } catch (IOException e) {
            System.out.println(ConsoleColors.RED + "CSV konnte nicht gelesen werden." + ConsoleColors.RESET);
        }
    }
}