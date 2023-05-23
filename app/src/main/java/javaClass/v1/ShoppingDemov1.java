package javaClass.v1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
public class ShoppingDemov1 {


    public void execute(){
        Product pie = new Product("pie", 50);
        Product shoes = new Product("Shoes", 150);
        Product hat = new Product("hat", 80);

        Customer customer = new Customer("Alex cruz",8846175954087535951L);

        ShoppingCart cart = new ShoppingCart(customer);
        cart.addProduct(pie);
        cart.addProduct(hat);
        cart.addProduct(shoes);
        if (cart.getProducts().isEmpty()) {
            println("Su carrito esta vacio");
        }
        else{
            if (cart.estado() == true){
                Order order = cart.checkout();
                println("The order is " + order);
            }
            else{
                println("Su saldo es insuficiente");
            }
        }







    }
    private void println(String s){
        System.out.println(s);
    }
}
