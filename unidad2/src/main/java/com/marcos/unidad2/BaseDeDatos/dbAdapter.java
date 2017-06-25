package com.marcos.unidad2.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class dbAdapter {
    private static final String DATABASE_NAME="db_curso_android";
    private static final int DATABASE_VERSION=1;

    private BaseDeDatosMestro data;
    private SQLiteDatabase db;

    /**
     * Constructor
     * @param context
     */
    public dbAdapter(Context context){
        data = new BaseDeDatosMestro(context,dbAdapter.DATABASE_NAME,null,dbAdapter.DATABASE_VERSION);
    }

    public void open(){
        db=data.getWritableDatabase();
        db.beginTransaction();
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }


    public long insertarResgistro(String tabla,ContentValues registros){
        long result=0;
        db=data.getWritableDatabase();
        db.beginTransaction();
        try{
            result=db.insert(tabla,null,registros);
            db.setTransactionSuccessful();
        }catch(SQLiteException ex){
            result=-1;
        }finally{
            db.endTransaction();
        }
        db.close();
        return result;
    }

    public boolean actualizarRegistro(String tabla,ContentValues registros,String clausuraWhere,String[] arg){
        boolean sw=false;
        db=data.getWritableDatabase();
        db.beginTransaction();
        try{
            db.update(tabla, registros, clausuraWhere, arg);
            db.setTransactionSuccessful();
            sw=true;
        }catch(SQLiteException ex){

        }finally{
            db.endTransaction();
        }
        db.close();
        return sw;
    }

    public int updateRegistros(String tabla,ContentValues registros,String clausuraWhere,String[] arg){
        int cont = 0;
        db=data.getWritableDatabase();
        db.beginTransaction();
        try{
            cont = db.update(tabla, registros, clausuraWhere, arg);
            db.setTransactionSuccessful();
        }catch(SQLiteException ex){
            cont = -1;
        }finally{
            db.endTransaction();
        }
        db.close();
        return cont;
    }

    public Cursor consultar(String tabla, String[] campos, String clausuraWhere, String[] arg, String Group, String clausuraHaving, String order){
        db=data.getReadableDatabase();
        Cursor cursor=null;
        try{
            cursor=db.query(tabla,campos,clausuraWhere,arg,Group,clausuraHaving,order);
        }catch(SQLiteException ex){

        }
        return cursor;
    }

    public Cursor consultaSql(String sql,String[] args){
        db=data.getReadableDatabase();
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(sql,args);
        }catch(SQLiteException ex){

        }
        return cursor;
    }

    public int eliminarRegistros(String tabla,String clausuraWhere,String[] args ){
        int delete = 0;
        db=data.getWritableDatabase();
        db.beginTransaction();
        try{
            delete=db.delete(tabla, clausuraWhere, args);
            db.setTransactionSuccessful();
        }catch(SQLiteException ex){
            delete = -1;
        }finally{
            db.endTransaction();
        }
        db.close();
        return delete;
    }

    public boolean sqlQuery(String sql){
        db=data.getWritableDatabase();
        db.beginTransaction();
        db.execSQL(sql);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return true;
    }

    /**
     * Cierra la BD
     */
    public void close(){
        db.close();
    }
}
