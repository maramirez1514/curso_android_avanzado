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

public class EditarClienteActivity extends AppCompatActivity {

    private EditText edtNombre1;
    private EditText edtApellido1;
    private EditText edtTelefono1;
    private EditText edtEmail1;
    private Button btnActualizar;
    private Button btnBorrar;
    private Bundle bundle;
    private long idcliente;
    private AccionesDB accionesDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_activity);
        setTitle("Editar");

        accionesDB = new AccionesDB(getApplicationContext());
        bundle = getIntent().getExtras();
        idcliente = bundle.getLong("idcliente");
        edtNombre1 = (EditText)findViewById(R.id.edtNombre1);
        edtNombre1.setText(bundle.getString("nombre"));
        edtApellido1 = (EditText)findViewById(R.id.edtApellido1);
        edtApellido1.setText(bundle.getString("apellido"));
        edtTelefono1 = (EditText)findViewById(R.id.edtTelefono1);
        edtTelefono1.setText(bundle.getString("telefono"));
        edtEmail1 = (EditText)findViewById(R.id.edtEmail1);
        edtEmail1.setText(bundle.getString("email"));
        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnBorrar = (Button)findViewById(R.id.btnBorrar);
        asignarEventos();
    }

    private void asignarEventos(){
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = new Cliente(idcliente,
                                              edtNombre1.getText().toString(),
                                              edtApellido1.getText().toString(),
                                              edtTelefono1.getText().toString(),
                                              edtEmail1.getText().toString());
                accionesDB.actualizarCliente(cliente);
                finish();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accionesDB.eliminarCliente(idcliente);
                finish();
            }
        });
    }
}