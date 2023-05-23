package javaClass.v1;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
public class ShoppingDemov1 {


    public void execute(){

        Catalogue catalogue = new Catalogue();


        Product pie = new Product("pie", 50);
        Product shoes = new Product("Shoes", 150);
        Product hat = new Product("hat", 80);

        catalogue.add(pie, 2);
        catalogue.add(shoes, 1);
        catalogue.add(hat, 1);


        Customer customer = new Customer("Alex cruz",8846175954087535951L);

        ShoppingCart cart = new ShoppingCart(customer, catalogue);

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
        println("Before order, catalogue is " + catalogue);
        Order order = cart.checkout();
        println("The order is " + order);
        println( "After order, catalogue is " + catalogue);







    }
    private void println(String s){
        System.out.println(s);
    }
}
