package com.marcos.unidad4;

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
 * Created by desarrollo on 03/07/17.
 */

public class ListaPostsActivity extends AppCompatActivity {

    private ListView lst_post;
    private JSONArray jsonArray;
    private ListaPostsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post);

        lst_post = (ListView)findViewById(R.id.lst_post);
        new Request().execute();
        asignarEventos();
    }

    private void cargarLista(JSONArray jsonArray){
        this.jsonArray = jsonArray;
        adapter = new ListaPostsAdapter(jsonArray,getApplicationContext());
        lst_post.setAdapter(adapter);
    }

    private void asignarEventos(){
        lst_post.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Intent intent = new Intent(getApplicationContext(),DetallePostActivity.class);
                    intent.putExtra("idpost",jsonArray.getJSONObject(position).getInt("id"));
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
                URL url = new URL("http://jsonplaceholder.typicode.com/posts");
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
