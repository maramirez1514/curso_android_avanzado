package com.marcos.examen_integrador.DataBases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.marcos.examen_integrador.Model.ListaTODO;
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

    public void cargarBaseLista(ArrayList<ListaTODO> arr_lista){
        ContentValues contentValues = new ContentValues();
        for(ListaTODO listaTODO:arr_lista){
            contentValues.put(BaseDeDatosMestro.TABLA_TAREAS_TEXTO,listaTODO.getTarea());
            contentValues.put(BaseDeDatosMestro.TABLA_TAREA_IMG,listaTODO.getUrl_imagen());
            contentValues.put(BaseDeDatosMestro.TABLA_TAREA_HECHO,listaTODO.getHecho());
            data.insertarResgistro(BaseDeDatosMestro.TABLA_TAREAS,contentValues);
            contentValues.clear();
        }
    }

    public ArrayList<ListaTODO>getListaTODO(){
        ArrayList<ListaTODO>arr_litatodo = new ArrayList<>();
        String campos[] = new String[]{BaseDeDatosMestro.TABLA_TAREAS_ID,    //0
                                       BaseDeDatosMestro.TABLA_TAREAS_TEXTO,   //1
                                       BaseDeDatosMestro.TABLA_TAREA_IMG,   //2
                                       BaseDeDatosMestro.TABLA_TAREA_HECHO};//4
        Cursor cursor = data.consultar(BaseDeDatosMestro.TABLA_TAREAS,campos,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                arr_litatodo.add(new ListaTODO(cursor.getString(2),cursor.getString(1),cursor.getInt(3)));
            }while (cursor.moveToNext());
            cursor.close();
        }
        return arr_litatodo;
    }
}
