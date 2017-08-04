package com.marcos.examen_integrador.DataBases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class BaseDeDatosMestro extends SQLiteOpenHelper {

    public static String TABLA_TAREAS = "tarea";
    public static String TABLA_TAREAS_ID = "_id";
    public static String TABLA_TAREAS_TEXTO = "texto";
    public static String TABLA_TAREA_IMG = "imagen";
    public static String TABLA_TAREA_HECHO = "hecho";
    public static String TABLA_TAREAS_CREATE = "CREATE TABLE " + TABLA_TAREAS + " (" +
                                                 TABLA_TAREAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                                 TABLA_TAREAS_TEXTO + " TEXT," +
                                                 TABLA_TAREA_IMG + " TEXT," +
                                                 TABLA_TAREA_HECHO + " INTEGER DEFAULT 0);";

    public BaseDeDatosMestro(Context contexto, String bdnombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, bdnombre, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_TAREAS_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}
}
