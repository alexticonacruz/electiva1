package javaClass.v1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A customer enters our system and has the following workflow:
 * Pre-Conditions:
 * - There must be products available
 * - There must be a Customer available
 * Normal execution:
 * -
 * Post-Conditions:
 * - You can see the product list
 * - You can see the total amount of prices
 * - You are able to create a Order with all the data in the ShoppingCart
 */
public class ShoppingCart {

    private final Customer customer;
    private final List<Product> products = new ArrayList<>();

    public ShoppingCart(Customer customer) {
        this.customer = customer;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public int getTotalCost() {
        int sum = 0;
        for (Product p : products) {
            sum += p.price;
        }
        return sum;
    }

    public Order checkout() {
        return new Order(
                customer,
                products,
                UUID.randomUUID().toString(),
                getTotalCost()
        );

    }
    public boolean estado(){
        if(customer.getCreditCard().getCredit() <= getTotalCost()){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
