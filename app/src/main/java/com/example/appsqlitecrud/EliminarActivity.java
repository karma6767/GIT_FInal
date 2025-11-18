package com.example.appsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EliminarActivity extends AppCompatActivity {

    Button btnEliminar;
    TextView txtMensaje;
    int codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        btnEliminar = findViewById(R.id.btnEliminar);
        txtMensaje = findViewById(R.id.txtMensaje);

        codigo = getIntent().getIntExtra("codigo", -1);

        btnEliminar.setOnClickListener(v -> {
            DBHelper db = new DBHelper(this);
            boolean ok = db.eliminarEstudiante(codigo);

            Toast.makeText(this, ok ? "Eliminado" : "Error", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}


