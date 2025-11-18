package com.example.appsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarActivity extends AppCompatActivity {

    EditText etNombre, etDescripcion;
    Button btnGuardar, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            DBHelper db = new DBHelper(this);
            boolean ok = db.insertarEstudiante(
                    etNombre.getText().toString(),
                    etDescripcion.getText().toString()
            );

            Toast.makeText(this, ok ? "Guardado" : "Error", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}


