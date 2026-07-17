package Repositories;

import DTO.Product;

import java.util.HashMap;
import java.util.Map;

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

    public Product getProductById(String productId) {
        return products.get(productId);
    }
}
