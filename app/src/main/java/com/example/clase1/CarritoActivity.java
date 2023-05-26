package com.example.clase1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Map;

import javaClass.v1.Customer;
import javaClass.v1.Product;
import javaClass.v2.CreditCard2;
import javaClass.v2.Customer2;
import javaClass.v2.SerializableMap;

public class CarritoActivity extends AppCompatActivity {

    private Customer2 customer2;
    private TextView textView;

    private Map<String, Integer> dictionary;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        textView = findViewById(R.id.editCarrito);

        /*Intent intent = getIntent();
        if(intent.getExtras() != null) {
            customer2 = intent.getParcelableExtra("persona");
            textView.setText(customer2.obtenerNombre() + " " + String.valueOf(customer2.obtenerCreditCard().getCardNumber()));

        }*/
        //  ---------------------------------------------------------------------
        // Recuperar el diccionario del Intent
        Intent intent = getIntent();

        if (intent.hasExtra("listInventario")) {
            SerializableMap listInventario = (SerializableMap) intent.getSerializableExtra("listInventario");
            // Utilizar el objeto listInventario según tus necesidades
            Map<String, Integer> inventory = listInventario.getMap();
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                // Realizar operaciones con cada entrada del diccionario
                // ...
            }
        }
        ArrayList<Product> productList = intent.getParcelableArrayListExtra("productList");


        // Utilizar el diccionario como desees
        if (dictionary != null) {
            // Bucle for
            for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Key: " + key + ", Value: " + value);
            }
        }



    }
}
