package com.marcos.reidyn.unidad6;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarNotificacion();
            }
        });
    }

    private void lanzarNotificacion(){
        int notificationId = 101;
        Intent viewIntent = new Intent(this, SegundaActivity.class);
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, viewIntent, 0);

        Intent urlIntent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse("http://www.google.com");
        urlIntent.setData(uri);
        PendingIntent urlPendingIntent = PendingIntent.getActivity(this, 0, urlIntent, 0);

        NotificationCompat.Builder mbuilder=new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getApplicationContext().getResources(),R.mipmap.ic_launcher))
                .setContentTitle("Notificacion")
                .setTicker(getResources().getString(R.string.app_name))
                .setContentText("Esto es una notificacion")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setLights(Color.YELLOW,500,500)
                .addAction(R.mipmap.ic_launcher, "Open Google", urlPendingIntent)
                .setContentIntent(viewPendingIntent);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, mbuilder.build());
    }
}
