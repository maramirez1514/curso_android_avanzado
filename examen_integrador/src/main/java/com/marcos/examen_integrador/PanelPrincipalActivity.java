package com.marcos.examen_integrador;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.marcos.examen_integrador.Adapter.ListaAdapter;
import com.marcos.examen_integrador.DataBases.AccionesDB;
import com.marcos.examen_integrador.Model.ListaTODO;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

/**
 * Created by windows 8.1 on 01/08/2017.
 */

public class PanelPrincipalActivity extends AppCompatActivity {

    private Button button;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RequestQueue mRequestQueue;
    private AccionesDB accionesDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_principal);

        accionesDB = new AccionesDB(getApplicationContext());
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        button = (Button)findViewById(R.id.button);
        mRecyclerView = (RecyclerView) findViewById(R.id.lst_to_do);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        asignarEventos();
    }

    private void asignarEventos(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                descargarDatos();
            }
        });
    }

    private void cargarDatos(ArrayList<ListaTODO>arrayList){
        accionesDB.cargarBaseLista(arrayList);
        mAdapter = new ListaAdapter(accionesDB.getListaTODO(),getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void descargarDatos(){
        StringRequest request=new StringRequest(Request.Method.GET, URLs.URL_LISTA_TODO, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONArray jsonArray = new JSONArray(s);
                    ArrayList<ListaTODO>arrayList = new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        arrayList.add(new ListaTODO(jsonArray.getJSONObject(i).getString("url_img"),
                                                    jsonArray.getJSONObject(i).getString("texto"),
                                                    jsonArray.getJSONObject(i).getInt("hecho")));
                    }
                    cargarDatos(arrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        mRequestQueue.add(request);
    }
}
