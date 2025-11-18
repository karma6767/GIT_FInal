package com.example.appsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btnListar, btnAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnListar = findViewById(R.id.btnListar);
        btnAgregar = findViewById(R.id.btnAgregar);

        btnListar.setOnClickListener(v ->
                startActivity(new Intent(MenuActivity.this, ListarActivity.class)));

        btnAgregar.setOnClickListener(v ->
                startActivity(new Intent(MenuActivity.this, AgregarActivity.class)));
    }
}

