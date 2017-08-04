package com.marcos.examen_integrador.Service;


import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.marcos.examen_integrador.R;
import com.marcos.examen_integrador.URLs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by windows 8.1 on 12/03/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getNotification() != null){
            String titulo = remoteMessage.getNotification().getTitle();
            String texto = remoteMessage.getNotification().getBody();
            showNotificacion(titulo, texto);
        }
    }

    private void showNotificacion(String titulo,String texto){
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap picture = null;
        try {
            picture = BitmapFactory.decodeStream((InputStream) new URL(URLs.URL_IMAGEN).getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(picture)
                .setContentTitle(titulo)
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText(texto)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setLights(Color.BLUE,500,500);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}
