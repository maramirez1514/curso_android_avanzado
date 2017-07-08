package com.marcos.ramirez;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.marcos.ramirez.BaseDeDatos.AccionesDB;
import com.marcos.ramirez.Modelo.Cliente;
import java.util.ArrayList;

/**
 * Created by windows 8.1 on 24/06/2017.
 */
public class ListaClientesActivity extends AppCompatActivity {

    private ListView listaCliente;
    private FloatingActionButton flagregarcliente;
    private ListaClientesAdapter adapter;
    private AccionesDB accionesDB;
    private ArrayList<Cliente> arr_clientes;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cliente);
        setTitle("Lista Clientes");

        accionesDB = new AccionesDB(getApplicationContext());
        listaCliente = (ListView)findViewById(R.id.listaCliente);
        flagregarcliente = (FloatingActionButton)findViewById(R.id.flagregarcliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        cargarLista();
        agregarEventos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        cargarLista();
    }

    private void cargarLista(){
        arr_clientes = accionesDB.getListaCliente();
        adapter = new ListaClientesAdapter(arr_clientes,getApplicationContext());
        listaCliente.setAdapter(adapter);
    }

    private void agregarEventos(){
        flagregarcliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),AgregarCliente.class));
            }
        });

        listaCliente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),EditarClienteActivity.class);
                intent.putExtra("idcliente",arr_clientes.get(position).getIdcliente());
                intent.putExtra("nombre",arr_clientes.get(position).getNombre());
                intent.putExtra("apellido",arr_clientes.get(position).getApellido());
                intent.putExtra("telefono",arr_clientes.get(position).getTelefono());
                intent.putExtra("email",arr_clientes.get(position).getEmail());
                startActivity(intent);
            }
        });
    }
}
