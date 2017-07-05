package com.marcos.unidad4_fotos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by windows 8.1 on 04/07/2017.
 */

public class DetalleFotoActivity extends AppCompatActivity {

    private ImageView img_fotos;
    private TextView txt_det_tittle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_foto);

        img_fotos = (ImageView)findViewById(R.id.img_fotos);
        txt_det_tittle = (TextView)findViewById(R.id.txt_det_tittle);

        Glide.with(getApplicationContext())
             .load(getIntent().getExtras().getString("url"))
             .into(img_fotos);
        txt_det_tittle.setText(getIntent().getExtras().getString("title"));
    }
}
