package com.example.appsqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "mi_db.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE IF NOT EXISTS ESTUDIANTES (" +
                        "CODIGO INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "NOMBRE TEXT, " +
                        "DESCRIPCION TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS ESTUDIANTES");
        onCreate(db);
    }

    public boolean insertarEstudiante(String nombre, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE", nombre);
        values.put("DESCRIPCION", descripcion);

        long result = db.insert("ESTUDIANTES", null, values);
        return result != -1;
    }

    public Cursor obtenerEstudiantes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM ESTUDIANTES", null);
    }

    public Cursor obtenerEstudiantePorCodigo(int codigo) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM ESTUDIANTES WHERE CODIGO = ?",
                new String[]{String.valueOf(codigo)});
    }

    public boolean actualizarEstudiante(int codigo, String nombre, String descripcion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE", nombre);
        values.put("DESCRIPCION", descripcion);

        int filas = db.update("ESTUDIANTES", values, "CODIGO = ?", new String[]{String.valueOf(codigo)});
        return filas > 0;
    }

    public boolean eliminarEstudiante(int codigo) {
        SQLiteDatabase db = this.getWritableDatabase();
        int filas = db.delete("ESTUDIANTES", "CODIGO = ?", new String[]{String.valueOf(codigo)});
        return filas > 0;
    }
}


