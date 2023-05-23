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
    private final Catalogue catalogue;
    private final List<Product> products = new ArrayList<>();

    public ShoppingCart(Customer customer, Catalogue catalogue) {
        this.customer = customer;
        this.catalogue = catalogue;
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
        for(Product p: products){ //reduce products from catalogue
            catalogue.remove(p);
        }
        return new Order(
                customer,
                products,
                UUID.randomUUID().toString(),
                getTotalCost()
        );
    }
    public List<Product> getProducts() {
        return products;
    }

    public boolean estado(){
        if(customer.getCreditCard().getCredit() >= getTotalCost()){
            imprimir(String.valueOf(customer.getCreditCard().getCredit()));
            return true;
        }
        else{
            return false;
        }
    }
    public void imprimir(String s){System.out.println(s);}


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}


