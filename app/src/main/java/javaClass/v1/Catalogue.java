package javaClass.v1;

import java.util.HashMap;
import java.util.Map;

public class Catalogue  {
    private final Map<String, Integer> products = new HashMap<>();//Dictionary

    public void add(Product product, int amount) {
        String key = product.getProductName();
        // if the product already exists, we just increase the amount to the previous amount
        if (products.containsKey(key)) {
            products.put(key, products.get(key) + amount);
        } else {
            products.put(key, amount);
        }
    }

    public void remove(Product product) {
        String key = product.getProductName();

        if (products.containsKey(key)) {
            products.put(key, products.get(key) - 1);
        }
    }

    @Override
    public String toString() {
        return "Catalogue{" +
                "products=" + products +
                '}';
    }
}
