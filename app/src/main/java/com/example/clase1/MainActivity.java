package com.example.clase1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Person;
import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javaClass.v1.Customer;
import javaClass.v1.Product;
import javaClass.v1.ShoppingDemov1;
import javaClass.v2.Catalogue2;
import javaClass.v2.CreditCard2;
import javaClass.v2.Customer2;
import javaClass.v2.Producto2;
import javaClass.v2.SerializableMap;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Customer2 customer2;
    private Producto2 producto2;
    private CreditCard2 creditCard2;
    private Catalogue2 catalogue2 = new Catalogue2();
    private int Contador = 0;

    private EditText nombreProducto;
    private EditText precioProducto;
    private EditText cantidadProducto;
    private EditText numeroTarjeta;
    private EditText dineroActual;
    private EditText usuario;
    private ConstraintLayout layout;

    private  Map<String, Integer> productsInventario = new HashMap<>();//Dictionary
    private  Map<Producto2, Integer> products = new HashMap<>();//Dictionary


    ArrayList<Producto2> productList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        nombreProducto = findViewById(R.id.edtNameProduct);
        precioProducto = findViewById(R.id.edtPriceProduct);
        cantidadProducto = findViewById(R.id.edtCantidadproducto);
        numeroTarjeta = findViewById(R.id.edtNumeroTarjeta);
        dineroActual = findViewById(R.id.edtMontoTarjeta);
        usuario = findViewById(R.id.edtUsuario);


        layout = findViewById(R.id.mainLayout);


    }

    //  ------------- metodo de agregar    -----------------
    public void agregar(View view){
        if(Contador == 0){
            creditCard2 = crearTarjeta();
            customer2 = AgregaUsuario(creditCard2);
            Contador++;
        }
        else{

        }
        if(addProductList()){
            catalogue2.add(producto2,Integer.parseInt(cantidadProducto.getText().toString()));
            usuario.setEnabled(false);
            numeroTarjeta.setEnabled(false);
            dineroActual.setEnabled(false);

            Toast.makeText(this, catalogue2.bbr(producto2.ObtenerNombre()), Toast.LENGTH_SHORT).show();
        }



    }
    public void GoSecond(View view){
        Intent intent = new Intent(MainActivity.this,CarritoActivity.class);

        // Crear un objeto Catalogue2 y agregar algunos productos
        //Catalogue2 catalogue = new Catalogue2();
        //Map<String, Integer> products = new HashMap<>();
        //products.put("Producto 1", 10);
        //products.put("Producto 2", 5);
        //catalogue.setProducts(products);

        // Crear un Intent y agregar el objeto Catalogue2 como extra

        intent.putExtra("catalogue", catalogue2);
        intent.putExtra("customer",customer2);
        intent.putExtra("ListaProductos",productList);
        //Toast.makeText(this, catalogue2.bbr(producto2.ObtenerNombre()), Toast.LENGTH_SHORT).show();

        startActivity(intent);
    }
    public void resetear(){
        nombreProducto.setText("");
        precioProducto.setText("");
        cantidadProducto.setText("");
    }
    //  -------     Creacion de Tarjeta --------------------------
    public CreditCard2 crearTarjeta(){
        long tarjeta = 8846175954087535951L;
        //long tarjeta =  Long.parseLong(numeroTarjeta.getText().toString());
        int monto = Integer.parseInt(dineroActual.getText().toString());
        CreditCard2 creditCardTemporal = new CreditCard2(tarjeta,monto);
        return creditCardTemporal;
    }
    //  ---------       Agrega Usuario  ---------------------
    public Customer2 AgregaUsuario(CreditCard2 card2){

        String nombreTemporal = usuario.getText().toString();

        Customer2 customerTemporal = new Customer2(nombreTemporal, card2);

        return customerTemporal;
    }
    // ---------    Agrega Producto a la lista  ------------
    // ---------    Metodo   -----------------
    public boolean addProductList (){
        producto2 = CrearProducto();
        if (existeProducto(producto2)) {

            Log.i(TAG,"Ups, Este producto se encuentra ya registrado");
            return false;
            //products.put(producto2,producto2.obtenerPrecio());
        }
        else{
            productList.add(producto2);
            return true;
        }
    }
    public Producto2 CrearProducto (){
        String nombre = nombreProducto.getText().toString();
        int precio = Integer.parseInt(precioProducto.getText().toString());

        Producto2 productoTemporal = new Producto2(nombre,precio);

        return productoTemporal;
    }
    public boolean existeProducto (Producto2 producto2){
        return productList.contains(producto2);
        //return products.containsKey(producto2);
    }

    //  ---------   Agrega Productos al inventario -------------

    public void SaveProducto(){
        String nombreLlave = nombreProducto.getText().toString();
        int cantidad = Integer.parseInt(cantidadProducto.getText().toString());

        agregarProducto(nombreLlave,cantidad);
    }
    public Map<String, Integer> CrearProductoInventario (){
        Catalogue2 catalogue2 = new Catalogue2();
        return catalogue2.obtenerProductos();
    }
    public void agregarProducto(String llave, int cantidad){
        if (ExisteElProducto(llave)){
            productsInventario.put(llave,cantidad);
        }
        else{Log.i(TAG,"Ups, ya registro este producto al inventario");}
    }

    public boolean ExisteElProducto(String clave){
        if(productsInventario.containsKey(clave)) return true;
        else{return false;}
    }
    //  --------------------------------------------------------------------------
    public void GoLogin (View view){
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    public void changeToGreen(View view) {
        Log.i(TAG,"btn1");
        int miColor = Color.rgb(255, 230, 0);
        layout.setBackgroundColor(miColor);
        demoShoppingCard();
    }

    public void GoSecondActivity(View view) {
        /*Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        Bundle b = new Bundle();
        b.putString("name",editTextName.getText().toString());
        intent.putExtras(b);
        demoShoppingCard();
        startActivity(intent);*/
        Log.i(TAG, "ANTES DE IR A LA SECOND ACTIVITY");
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // Bundle b = new Bundle();
        //b.putString("name", editTextName.getText().toString());
        Product product = new Product("Dress",100);
        intent.putExtra("product",product);
        startActivity(intent);

    }

    public void abrir(View view) {

        demoShoppingCard();
    }


    private void demoShoppingCard (){
        new ShoppingDemov1().execute();
    }
}