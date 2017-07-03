package com.marcos.unidad4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by desarrollo on 03/07/17.
 */

public class ListaPostsAdapter extends BaseAdapter{

    private JSONArray jsonArray;
    private LayoutInflater mInflater;

    public ListaPostsAdapter(JSONArray jsonArray, Context context) {
        this.jsonArray = jsonArray;
        this.mInflater = LayoutInflater.from(context);
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
        return  null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_list_posts, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txt_item_id = (TextView)convertView.findViewById(R.id.txt_item_id);
            viewHolder.txt_item_title = (TextView)convertView.findViewById(R.id.txt_item_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        try {
            JSONObject jsonObject = jsonArray.getJSONObject(position);
            viewHolder.txt_item_id.setText(jsonObject.getInt("id")+"");
            viewHolder.txt_item_title.setText(jsonObject.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return convertView;
    }

    class ViewHolder {
        TextView txt_item_id;
        TextView txt_item_title;
    }
}
