package com.marcos.unidad4_fotos;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by windows 8.1 on 04/07/2017.
 */

public class ListaFotosActivity extends AppCompatActivity{

    private ListView lst_fotos;
    private JSONArray jsonArray;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_fotos);

        lst_fotos = (ListView)findViewById(R.id.lst_fotos);
        new Request().execute();
        asignarEventos();
    }

    private void cargarLista(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        lst_fotos.setAdapter(new ListaFotosAdapter(jsonArray,getApplicationContext()));
    }

    private void asignarEventos(){
        lst_fotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                try {
                    Intent intent = new Intent(getApplicationContext(),DetalleFotoActivity.class);
                    intent.putExtra("url",jsonArray.getJSONObject(position).getString("url"));
                    intent.putExtra("title",jsonArray.getJSONObject(position).getString("title"));
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private class Request extends AsyncTask<Void,Void,String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            try {
                URL url = new URL("http://jsonplaceholder.typicode.com/photos");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                result = parseResponse(in);
                urlConnection.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray jsonArray = new JSONArray(s);
                cargarLista(jsonArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String parseResponse(InputStream in){
            StringBuilder sb = new StringBuilder();;
            try {
                BufferedReader reader=new BufferedReader(new InputStreamReader(in,"iso-8859-1"),8);
                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                in.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }
    }
}
