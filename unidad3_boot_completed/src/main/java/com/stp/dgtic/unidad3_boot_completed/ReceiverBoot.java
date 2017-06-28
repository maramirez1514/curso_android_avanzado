package com.stp.dgtic.unidad3_boot_completed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by desarrollo on 28/06/17.
 */

public class ReceiverBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Arranque completo",Toast.LENGTH_LONG).show();
    }
}
