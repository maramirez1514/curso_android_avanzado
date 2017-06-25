package com.marcos.unidad2.BaseDeDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class BaseDeDatosMestro extends SQLiteOpenHelper {

    public static String TABLA_CLIENTE = "cliente";
    public static String TABLA_CLIENTE_ID = "_id";
    public static String TABLA_CLIENTE_NOM = "nombre";
    public static String TABLA_CLIENTE_APE = "apellido";
    public static String TABLA_CLIENTE_TEL = "telefono";
    public static String TABLA_CLIENTE_EMAIL = "email";
    public static String TABLA_CLIENTE_CREATE = "CREATE TABLE " + TABLA_CLIENTE + " (" +
                                                 TABLA_CLIENTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                 TABLA_CLIENTE_NOM + " TEXT," +
                                                 TABLA_CLIENTE_APE + " TEXT," +
                                                 TABLA_CLIENTE_TEL + " TEXT," +
                                                 TABLA_CLIENTE_EMAIL + " TEXT);";

    public BaseDeDatosMestro(Context contexto, String bdnombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, bdnombre, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_CLIENTE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}
}
