package com.marcos.unidad2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.marcos.unidad2.BaseDeDatos.AccionesDB;
import com.marcos.unidad2.Modelo.Cliente;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cliente);
        setTitle("AGREGAR CLIENTE");

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
