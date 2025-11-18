package com.example.appsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerActivity extends AppCompatActivity {

    TextView tvCodigo, tvNombre, tvDescripcion;
    Button btnEditar, btnEliminar;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        tvCodigo = findViewById(R.id.tvCodigo);
        tvNombre = findViewById(R.id.tvNombre);
        tvDescripcion = findViewById(R.id.tvDescripcion);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        codigo = getIntent().getIntExtra("codigo", -1);
        if (codigo == -1) { finish(); }

        cargarDatos();

        btnEditar.setOnClickListener(v -> {
            Intent intent = new Intent(this, EditarActivity.class);
            intent.putExtra("codigo", codigo);
            startActivity(intent);
        });

        btnEliminar.setOnClickListener(v -> {
            Intent intent = new Intent(this, EliminarActivity.class);
            intent.putExtra("codigo", codigo);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarDatos();
    }

    private void cargarDatos() {
        DBHelper db = new DBHelper(this);
        Cursor cursor = db.obtenerEstudiantePorCodigo(codigo);

        if (cursor.moveToFirst()) {
            tvCodigo.setText("Código: " + cursor.getInt(0));
            tvNombre.setText("Nombre: " + cursor.getString(1));
            tvDescripcion.setText("Descripción: " + cursor.getString(2));
        }
    }
}


