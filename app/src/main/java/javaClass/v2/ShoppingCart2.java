package javaClass.v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javaClass.v1.Catalogue;
import javaClass.v1.Customer;
import javaClass.v1.Order;
import javaClass.v1.Product;

public class ShoppingCart2 {
    private  Customer2 customer;
    private  Catalogue2 catalogue;
    private final List<Product> products = new ArrayList<>();
    private List<Producto2> productosLista = new ArrayList<>();

    private  Map<String, Integer> productosCantidad = new HashMap<>();//Dictionary


    public ShoppingCart2(Customer2 customer, Catalogue2 catalogue) {
        this.customer = customer;
        this.catalogue = catalogue;
    }
    public Map<String,Integer> obtenerCantidadProductos(){
        return  productosCantidad;
    }
    public List<Producto2> obtenerListaProductos(){
        return productosLista;
    }

    public void addProduct(Producto2 product) {
        productosLista.add(product);
        actualizaCantidad(product.ObtenerNombre());
    }

    public void actualizaCantidad(String nombre){
        if(productosCantidad.containsKey(nombre)){
            int actual = productosCantidad.get(nombre) + 1;
            productosCantidad.put(nombre,actual);
        }
        else {
            productosCantidad.put(nombre,1);
        }
    }


    public int getTotalCost() {
        int sum = 0;
        for (Producto2 p : productosLista) {
            sum += p.obtenerPrecio();
        }
        return sum;
    }

    public Order2 checkout() {

        for(Producto2 p: productosLista){ //reduce products from catalogue
            catalogue.remove(p);
        }
        return new Order2(
                customer,
                productosLista,
                UUID.randomUUID().toString(),
                getTotalCost()
        );
    }
    public List<Producto2> getProducts() {
        return productosLista;
    }

    public boolean estado(){
        return customer.obtenerCreditCard().getCredit() >= getTotalCost();
        //if(customer.obtenerCreditCard().getCredit() >= getTotalCost()){
          //  imprimir(String.valueOf(customer.obtenerCreditCard().getCredit()));
            //return true;
        //}
        //else{
          //  return false;
        //}
    }
    public void imprimir(String s){System.out.println(s);}


    @Override
    public String toString() {
        return "ShoppingCart{" +
                "products=" + products +
                '}';
    }
}
