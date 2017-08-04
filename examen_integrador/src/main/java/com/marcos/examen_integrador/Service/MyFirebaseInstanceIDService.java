package com.marcos.examen_integrador.Service;


import android.content.Intent;
import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by windows 8.1 on 12/03/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.i("Token",refreshedToken);
    }
}
