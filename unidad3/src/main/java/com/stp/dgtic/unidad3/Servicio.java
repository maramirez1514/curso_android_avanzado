package com.stp.dgtic.unidad3;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

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
 * Created by desarrollo on 27/06/17.
 */

public class Servicio extends Service{

    public static final String ACTION_MY_SERVICE = "com.stp.dgtic.unidad3.RESPONSE";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        realizarConexion();
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                realizarConexion();
                return null;
            }
        }.execute();
    }

    private void realizarConexion(){
        String result = "";
        try {
            URL url = new URL("http://www.android.com/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = parseResponse(in);
            sentBroadcast(result);
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        stopSelf();
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

    private void sentBroadcast(String result){
        Intent intentResponse = new Intent();
        intentResponse.setAction(ACTION_MY_SERVICE);
        intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
        intentResponse.putExtra("result",result);
        sendBroadcast(intentResponse);
    }
}
