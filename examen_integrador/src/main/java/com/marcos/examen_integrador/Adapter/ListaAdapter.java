package com.marcos.examen_integrador.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.marcos.examen_integrador.Model.ListaTODO;
import com.marcos.examen_integrador.R;

import java.util.ArrayList;

/**
 * Created by windows 8.1 on 02/08/2017.
 */

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private ArrayList<ListaTODO> arr_tareas;
    private Context context;

    public ListaAdapter(ArrayList<ListaTODO> arr_tareas,Context context) {
        this.arr_tareas = arr_tareas;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(arr_tareas.get(position).getUrl_imagen()).into(holder.item_imagen);
        holder.item_texto.setText(arr_tareas.get(position).getTarea());
        holder.item_check.setChecked((arr_tareas.get(position).getHecho()==1));
    }

    @Override
    public int getItemCount() {
        return arr_tareas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView item_imagen;
        private TextView item_texto;
        private CheckBox item_check;

        public ViewHolder(View view) {
            super(view);
            item_imagen = (ImageView)view.findViewById(R.id.item_imagen);
            item_texto = (TextView)view.findViewById(R.id.item_texto);
            item_check = (CheckBox)view.findViewById(R.id.item_check);
        }
    }
}
