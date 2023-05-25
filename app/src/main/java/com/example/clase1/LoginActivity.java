package com.example.clase1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javaClass.v1.Product;
import javaClass.v2.CreditCard2;
import javaClass.v2.Customer2;

public class LoginActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText numeroCard;
    private EditText monto;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nombre = findViewById(R.id.edtName);
        numeroCard = findViewById(R.id.edtNumberCard);
        monto = findViewById(R.id.edtMonto);


    }

    public void guardar(View view){
        String nombres = nombre.getText().toString();

        String number = numeroCard.getText().toString() + "L";
        long numbers = 8846175954087535951L;

        int montos = Integer.parseInt(monto.getText().toString());
        CreditCard2 creditCard2 = new CreditCard2(numbers,montos);

        Customer2 persona = new Customer2(nombres,creditCard2);

        Intent intent = new Intent(LoginActivity.this, CarritoActivity.class);

        intent.putExtra("persona",persona);

        startActivity(intent);
    }
}
