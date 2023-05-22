package com.example.clase1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javaClass.v1.ShoppingDemov1;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private EditText editTextName;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        editTextName = findViewById(R.id.editTextText);
        layout = findViewById(R.id.mainLayout);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"btn2");
                int miColor = Color.rgb(255, 0, 0); // Color rojo (RGB: 255, 0, 0)
                btn2.setBackgroundColor(miColor); // Establecer color rojo personalizado
                layout.setBackgroundColor(miColor);

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"btn2");
                int miColor = Color.rgb(255, 0, 0); // Color rojo (RGB: 255, 0, 0)
                layout.setBackgroundColor(miColor);
                demoShoppingCard();

            }
        });
    }

    public void changeToGreen(View view) {
        Log.i(TAG,"btn1");
        int miColor = Color.rgb(255, 230, 0);
        layout.setBackgroundColor(miColor);
    }

    public void GoSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        Bundle b = new Bundle();
        b.putString("name",editTextName.getText().toString());
        intent.putExtras(b);
        demoShoppingCard();
        startActivity(intent);
    }

    private void demoShoppingCard (){
        new ShoppingDemov1().execute();
    }
}