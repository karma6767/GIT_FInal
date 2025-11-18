package com.example.appsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarActivity extends AppCompatActivity {

    EditText etNombre, etDescripcion;
    Button btnActualizar;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        etNombre = findViewById(R.id.etNombre);
        etDescripcion = findViewById(R.id.etDescripcion);
        btnActualizar = findViewById(R.id.btnActualizar);

        codigo = getIntent().getIntExtra("codigo", -1);

        cargarDatos();

        btnActualizar.setOnClickListener(v -> {
            DBHelper db = new DBHelper(this);
            boolean ok = db.actualizarEstudiante(
                    codigo,
                    etNombre.getText().toString(),
                    etDescripcion.getText().toString()
            );

            Toast.makeText(this, ok ? "Actualizado" : "Error", Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    private void cargarDatos() {
        DBHelper db = new DBHelper(this);
        Cursor cursor = db.obtenerEstudiantePorCodigo(codigo);

        if (cursor.moveToFirst()) {
            etNombre.setText(cursor.getString(1));
            etDescripcion.setText(cursor.getString(2));
        }
    }
}



