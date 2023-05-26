package com.example.clase1;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import javaClass.v1.Product;

public class SecondActivity extends AppCompatActivity {
    private TextView textNombre;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
            /*super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_second);
            textNombre = findViewById(R.id.textView);
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();

        if (bundle != null) {
            String name = bundle.getString("name");
            // Haz algo con el valor "name"
            textNombre.setText(name);*/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);




    }
}
