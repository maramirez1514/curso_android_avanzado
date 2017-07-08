package com.marcos.ramirez.BaseDeDatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.marcos.ramirez.Modelo.Cliente;
import java.util.ArrayList;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class AccionesDB {
    private dbAdapter data;
    private Context context;

    public AccionesDB(Context context) {
        this.context = context;
        data = new dbAdapter(context);
    }

    public long agregarCliente(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_NOM,cliente.getNombre());
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_APE,cliente.getApellido());
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_TEL,cliente.getTelefono());
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_EMAIL,cliente.getEmail());
        return data.insertarResgistro(BaseDeDatosMestro.TABLA_CLIENTE,contentValues);
    }

    public ArrayList<Cliente>getListaCliente(){
        ArrayList<Cliente>arr_clientes = new ArrayList<>();
        String campos[] = new String[]{BaseDeDatosMestro.TABLA_CLIENTE_ID,    //0
                                       BaseDeDatosMestro.TABLA_CLIENTE_NOM,   //1
                                       BaseDeDatosMestro.TABLA_CLIENTE_APE,   //2
                                       BaseDeDatosMestro.TABLA_CLIENTE_TEL,   //3
                                       BaseDeDatosMestro.TABLA_CLIENTE_EMAIL};//4
        Cursor cursor = data.consultar(BaseDeDatosMestro.TABLA_CLIENTE,campos,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                arr_clientes.add(new Cliente(cursor.getLong(0),
                                             cursor.getString(1),
                                             cursor.getString(2),
                                             cursor.getString(3),
                                             cursor.getString(4)));
            }while (cursor.moveToNext());
            cursor.close();
        }
        return arr_clientes;
    }

    public void actualizarCliente(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_NOM,cliente.getNombre());
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_APE,cliente.getApellido());
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_TEL,cliente.getTelefono());
        contentValues.put(BaseDeDatosMestro.TABLA_CLIENTE_EMAIL,cliente.getEmail());
        data.actualizarRegistro(BaseDeDatosMestro.TABLA_CLIENTE,contentValues,BaseDeDatosMestro.TABLA_CLIENTE_ID+"="+cliente.getIdcliente(),null);
    }

    public void eliminarCliente(long idcliente){
        data.eliminarRegistros(BaseDeDatosMestro.TABLA_CLIENTE,BaseDeDatosMestro.TABLA_CLIENTE_ID+"="+idcliente,null);
    }
}
