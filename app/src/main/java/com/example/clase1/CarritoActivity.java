package com.example.clase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javaClass.v1.Customer;
import javaClass.v1.Product;
import javaClass.v2.Catalogue2;
import javaClass.v2.CreditCard2;
import javaClass.v2.Customer2;
import javaClass.v2.Order2;
import javaClass.v2.Producto2;
import javaClass.v2.SerializableMap;
import javaClass.v2.ShoppingCart2;

public class CarritoActivity extends AppCompatActivity {

    private Customer2 customer2;
    private ShoppingCart2 carrito ;
    private TextView textViewTitulo;
    private LinearLayout container;
    private Map<String, Integer> dictionary;
    private HashMap<Button, String> buttonMap;
    private ArrayList<Producto2> ListaProductos = new ArrayList<>();
    private Catalogue2 catalogue;
    private Order2 orden;
    private ScrollView  ScrollCards;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Inicializar el HashMap para asociar botones con nombres de cards
        buttonMap = new HashMap<>();

        textViewTitulo = findViewById(R.id.editCarrito);
        container = findViewById(R.id.cards);
        customer2 = getIntent().getParcelableExtra("customer");
        ScrollCards = findViewById(R.id.scrollCards);
        ListaProductos = getIntent().getParcelableArrayListExtra("ListaProductos");
        // Obtener el objeto Catalogue2 del Intent
        catalogue = getIntent().getParcelableExtra("catalogue");

        carrito = new ShoppingCart2 (customer2,catalogue);


        //Toast.makeText(this, catalogue.bbr(), Toast.LENGTH_SHORT).show();


        textViewTitulo.setText("Bienvenido " + customer2.obtenerNombre());

        // Acceder a los productos del catálogo y hacer algo con ellos
        if (catalogue != null) {
            Map<String, Integer> products = catalogue.getProducts();
            for (Map.Entry<String, Integer> entry : products.entrySet()) {
                String productName = entry.getKey();
                int quantity = entry.getValue();
                // Hacer algo con el nombre del producto y la cantidad
                for (Producto2 producto : ListaProductos) {


                    String Temporal1 = producto.ObtenerNombre();
                    String Temporal2 = productName;

                    if(Temporal1.equals(productName)){

                        LinearLayout cardLayout = AddProducts(productName, quantity,producto.obtenerPrecio());
                        //Toast.makeText(this, "Entro al equals", Toast.LENGTH_SHORT).show();
                        // Agregar el card al contenedor
                        container.addView(cardLayout);
                    }
                    else{

                        //Toast.makeText(this, "No entro al equals", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
    }
    public LinearLayout AddProducts(String productName, int stock, int precio){

        // Crear un nuevo card
        LinearLayout cardLayout = new LinearLayout(this);
        cardLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        cardLayout.setOrientation(LinearLayout.VERTICAL);

        // Crear ImageView y establecer atributos
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        imageView.setImageResource(R.mipmap.corei5); // Reemplaza con tu imagen correspondiente
        cardLayout.addView(imageView);

        // Crear TextView y establecer atributos
        TextView textView = new TextView(this);
        textView.setLayoutParams(new LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT));

        textView.setText(productName + "Precio : "+ precio + " Stock : " + stock );
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        cardLayout.addView(textView);



        // Crear Button y establecer atributos
        Button button = new Button(this);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        button.setText("Agregar");



        // Obtener el nombre de la card correspondiente al botón
        String cardName = productName;

        // Asociar el botón con el nombre de la card en el HashMap
        buttonMap.put(button, cardName);

        // Establecer un OnClickListener para el botón
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener el nombre de la card cuando se hace clic en el botón
                String clickedCardName = buttonMap.get(button);

                // Realizar acciones con el nombre de la card
                // ...
                textViewTitulo.setText(clickedCardName);
                listCarrito(clickedCardName);
            }
        });
        cardLayout.addView(button);
        return cardLayout;
    }
    public void listCarrito(String keyNombre) {
        Producto2 temporalProducto = null;
        for (Producto2 producto : ListaProductos) {
            if (producto.ObtenerNombre().equals(keyNombre)) {
                temporalProducto = producto;
                carrito.addProduct(temporalProducto);
                break; // Romper el bucle si se encuentra el objeto deseado
            }
        }
    }
    
    public void comprar (View view){
        if(carrito.estado()){
            if(verificaStock()){
                orden = carrito.checkout();
                ScrollCards.setVisibility(View.GONE);
                cargarListaOrden();
            }
            else{
                Toast.makeText(this, "No se pudo completar su compra, por falta de productos", Toast.LENGTH_SHORT).show();
            }
            

        }
        else{
            Toast.makeText(this, "No se pudo completar su comprar, su saldo es insuficiente", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean verificaStock(){
        boolean estado = false;
        Map<String,Integer> stock = catalogue.getProducts();
        Map<String, Integer> cantidad = carrito.obtenerCantidadProductos();
        for (Map.Entry<String, Integer> entry : cantidad.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();

            if(stock.get(productName) >= quantity){
                estado = true;
            }
            else{
                estado = false;
                break;
            }

        }
        return estado;
    }


    public void cargarListaOrden(){

        Map<String, Integer> products = carrito.obtenerCantidadProductos();
        LinearLayout container = findViewById(R.id.LinearOrder);
        TextView nameCliente = findViewById(R.id.tvCliente);
        nameCliente.setText("Cliente :" +orden.obtenerCustomer().obtenerNombre());
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            String productName = entry.getKey();
            int quantity = entry.getValue();
            int precioProductoTotal = 0;
            int precioUnitario = 0;
            for (Producto2 producto:carrito.obtenerListaProductos()) {
                if(productName.equals(producto.ObtenerNombre())){
                    precioProductoTotal += producto.obtenerPrecio();
                }
                precioUnitario = producto.obtenerPrecio();
            }
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            // Crear el primer TextView
            TextView textView1 = new TextView(this);
            textView1.setText("Producto : " + productName + "Cantidad : " + quantity + " Total " + precioProductoTotal);

            // Crear el segundo TextView
            TextView textView2 = new TextView(this);
            textView2.setText("Producto : " + productName + "Precio Unitario :" + precioUnitario);

            // Agregar los TextView al LinearLayout
            linearLayout.addView(textView1);
            linearLayout.addView(textView2);
            container.addView(linearLayout);

        }
        TextView totalbs = findViewById(R.id.tvTotal);
        totalbs.setText("Total :" + orden.obtenerTotal());

    }

}
