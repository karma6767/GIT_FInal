package com.example.appsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class ListarActivity extends AppCompatActivity {

    ListView listView;
    Button btnIrAgregar;
    ArrayList<String> lista;
    ArrayList<Integer> codigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listView = findViewById(R.id.listViewEstudiantes);
        btnIrAgregar = findViewById(R.id.btnIrAgregar);

        cargarListado();

        btnIrAgregar.setOnClickListener(v ->
                startActivity(new Intent(ListarActivity.this, AgregarActivity.class)));

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ListarActivity.this, VerActivity.class);
            intent.putExtra("codigo", codigos.get(position));
            startActivity(intent);
        });
    }

    private void cargarListado() {
        DBHelper db = new DBHelper(this);
        Cursor cursor = db.obtenerEstudiantes();

        lista = new ArrayList<>();
        codigos = new ArrayList<>();

        while (cursor.moveToNext()) {
            codigos.add(cursor.getInt(0));
            lista.add(cursor.getInt(0) + " - " + cursor.getString(1));
        }

        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista));
    }
}



