package DTO;

public record Product(String id, String name, String description, double price, double amount) {

    public Product withPrice(double price) {
        return  new Product(this.id, this.name, this.description, price, this.amount);
    }

    public Product withAmount(double amount) {
        return  new Product(this.id, this.name, this.description, this.price, amount);
    }
}
