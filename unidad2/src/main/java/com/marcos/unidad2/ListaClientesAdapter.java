package com.marcos.unidad2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.marcos.unidad2.Modelo.Cliente;
import java.util.ArrayList;

/**
 * Created by windows 8.1 on 24/06/2017.
 */

public class ListaClientesAdapter extends BaseAdapter{

    private ArrayList<Cliente>arr_clientes;
    private LayoutInflater mInflater;

    public ListaClientesAdapter(ArrayList<Cliente> arr_clientes, Context context) {
        this.arr_clientes = arr_clientes;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arr_clientes.size();
    }

    @Override
    public Object getItem(int position) {
        return arr_clientes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtnombre = (TextView)convertView.findViewById(R.id.txtnombre);
            viewHolder.txtapellido = (TextView)convertView.findViewById(R.id.txtapellido);
            viewHolder.txttelefono = (TextView)convertView.findViewById(R.id.txttelefono);
            viewHolder.txtemail = (TextView)convertView.findViewById(R.id.txtemail);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtnombre.setText(arr_clientes.get(position).getNombre());
        viewHolder.txtapellido.setText(arr_clientes.get(position).getApellido());
        viewHolder.txttelefono.setText(arr_clientes.get(position).getTelefono());
        viewHolder.txtemail.setText(arr_clientes.get(position).getEmail());
        return convertView;
    }

    class ViewHolder {
        TextView txtnombre;
        TextView txtapellido;
        TextView txttelefono;
        TextView txtemail;
    }
}
