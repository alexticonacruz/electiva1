package javaClass.v1;

import java.util.List;

public class Order {
    private final Customer customer;
    private final List<Product> products;
    private final String transaction;
    private final int amount;

    public Order(Customer customer, List<Product> products, String transaction, int amount) {
        this.customer = customer;
        this.products = products;
        this.transaction = transaction;
        this.amount = amount;
    }


    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", products=" + products +
                ", transaction='" + transaction + '\'' +
                ", amount=" + amount +
                '}';
    }
}
