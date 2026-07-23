package Repositories;

import DTO.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepo {
    private final Map<String,Product> products = new HashMap<>();

    public Map<String, Product> getAllProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    public void deleteProduct(String productId) {
        products.remove(productId);
    }

    public Optional<Product> getProductById(String productId) {
        if(products.get(productId) == null) {
            return Optional.empty();
        }
        return  Optional.of(products.get(productId));
    }
}
