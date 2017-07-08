package com.marcos.ramirez;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.marcos.ramirez.BaseDeDatos.AccionesDB;
import com.marcos.ramirez.Modelo.Cliente;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class AgregarCliente extends AppCompatActivity {

    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtTelefono;
    private EditText edtEmail;
    private Button btnGuardar;
    private AccionesDB accionesDB;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        setTitle("AGREGAR CLIENTE");

        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        accionesDB = new AccionesDB(getApplicationContext());
        edtNombre = (EditText)findViewById(R.id.edtNombre);
        edtApellido = (EditText)findViewById(R.id.edtApellido);
        edtTelefono = (EditText)findViewById(R.id.edtTelefono);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        btnGuardar = (Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = new Cliente(edtNombre.getText().toString(),
                                              edtApellido.getText().toString(),
                                              edtTelefono.getText().toString(),
                                              edtEmail.getText().toString());
                accionesDB.agregarCliente(cliente);
                finish();
            }
        });
    }
}
