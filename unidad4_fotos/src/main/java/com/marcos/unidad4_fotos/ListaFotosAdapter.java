package com.marcos.unidad4_fotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by windows 8.1 on 04/07/2017.
 */

public class ListaFotosAdapter extends BaseAdapter {

    private JSONArray jsonArray;
    private LayoutInflater mInflater;
    private Context context;

    public ListaFotosAdapter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        try {
            return jsonArray.get(position);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_fotos, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_album_id= (TextView)convertView.findViewById(R.id.txt_album_id);
            viewHolder.txt_id = (TextView)convertView.findViewById(R.id.txt_id);
            viewHolder.txt_title = (TextView)convertView.findViewById(R.id.txt_title);
            viewHolder.img_thumbnail = (ImageView)convertView.findViewById(R.id.img_thumbnail);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            viewHolder.txt_album_id.setText("albumId :" + jsonObject.getInt("albumId")+"");
            viewHolder.txt_id.setText("id :" + jsonObject.getInt("id")+"");
            viewHolder.txt_title.setText("title :" + jsonObject.getString("title"));
            Glide.with(context).load(jsonObject.getString("thumbnailUrl")).into(viewHolder.img_thumbnail);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    class ViewHolder {
        TextView txt_album_id;
        TextView txt_id;
        TextView txt_title;
        ImageView img_thumbnail;
    }
}
