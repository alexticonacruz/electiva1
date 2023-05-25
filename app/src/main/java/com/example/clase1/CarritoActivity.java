package com.example.clase1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javaClass.v1.Customer;
import javaClass.v1.Product;
import javaClass.v2.CreditCard2;
import javaClass.v2.Customer2;

public class CarritoActivity extends AppCompatActivity {

    private Customer2 customer2;
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        textView = findViewById(R.id.editCarrito);

        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            Customer2 customer = intent.getParcelableExtra("persona");
            textView.setText(customer.obtenerNombre() + " " + String.valueOf(customer.obtenerCreditCard().getCardNumber()));

        }

    }
}
