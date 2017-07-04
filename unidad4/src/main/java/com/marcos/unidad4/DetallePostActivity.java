package com.marcos.unidad4;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
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

public class DetallePostActivity extends AppCompatActivity {

    private TextView txt_user_id;
    private TextView txt_id;
    private TextView txt_title;
    private TextView txt_body;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_post_activity);

        txt_user_id = (TextView)findViewById(R.id.txt_user_id);
        txt_id = (TextView)findViewById(R.id.txt_id);
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_body = (TextView)findViewById(R.id.txt_body);
        new Request().execute(getIntent().getExtras().getInt("idpost")+"");
    }

    private void parsearResult(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            txt_user_id.setText("userId :" + jsonObject.getString("userId"));
            txt_id.setText("id :" + jsonObject.getInt("id"));
            txt_title.setText("title :" + jsonObject.getString("title"));
            txt_body.setText("body :" + jsonObject.getString("body"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private class Request extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                URL url = new URL("http://jsonplaceholder.typicode.com/posts/" + params[0]);
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
            parsearResult(s);
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
