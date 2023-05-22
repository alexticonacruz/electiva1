package javaClass.v1;

public class Product {

    private final String productName;
    public int price;

    public Product(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "\n" + "Product{" +
                "name='" + productName + '\'' +
                ", price=" + price +
                '}';
    }


}
