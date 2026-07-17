package Repositories;

import DTO.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ProductRepoTest {

    @Test
    void getAllProducts_shouldReturnAllProducts_whenCalledWithMoreAddedProducts() {

        ProductRepo repo = new ProductRepo();
        Product product1 = new Product("1", "Laptop", "HP Laptop 2026",999.99, 5);
        Product product2 = new Product("2", "Maus", "Wireless Maus",29.99, 10);

        repo.addProduct(product1);
        repo.addProduct(product2);

        assertThat(repo.getAllProducts())
                .hasSize(2)
                .containsEntry("1", product1).containsEntry("2", product2);
    }

    @Test
    void addProduct_shouldAddProductToRepo_whenCalledWithOneProduct() {
        ProductRepo repo = new ProductRepo();
        Product product = new Product("1", "Laptop", "HP Laptop 2026",999.99, 0);

        repo.addProduct(product);

        assertThat(repo.getAllProducts())
                .hasSize(1)
                .containsEntry("1", product);
    }

    @Test
    void deleteProduct_shouldDeleteProductFromRepo_whenCalledWithOneProductAndDeleteIt() {
        ProductRepo repo = new ProductRepo();
        Product product = new Product("1", "Laptop", "HP Laptop 2026",999.99, 0);

        repo.addProduct(product);

        repo.deleteProduct(product.id());

        assertThat(repo.getAllProducts())
                .hasSize(0);
    }

    @Test
    void getProductById_shouldReturnProductFromRepo_whenCalledWithOneProduct() {

        ProductRepo repo = new ProductRepo();
        Product product = new Product("1", "Laptop", "HP Laptop 2026",999.99, 0);

        repo.addProduct(product);

        Product expected = repo.getProductById(product.id());

        assertEquals(expected, product);
    }
}