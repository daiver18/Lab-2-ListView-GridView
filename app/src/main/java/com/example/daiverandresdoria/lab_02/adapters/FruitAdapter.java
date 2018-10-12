package com.example.daiverandresdoria.lab_02.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daiverandresdoria.lab_02.R;
import com.example.daiverandresdoria.lab_02.models.Fruit;

import java.util.List;

public class FruitAdapter extends BaseAdapter{

    private Context context;
    private int layout;
    private List<Fruit> list;

    public FruitAdapter(Context context, int layout, List<Fruit> list){
        this.context = context;
        this.layout = layout;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fruit getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(this.layout,null);

            holder = new ViewHolder();
            holder.TextViewName = (TextView) convertView.findViewById(R.id.textViewName);
            holder.TextVewOrigin = (TextView) convertView.findViewById(R.id.textViewOrigin);
            holder.ImagenViewIcon = (ImageView) convertView.findViewById(R.id.ImagenViewIcon);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        final Fruit currentFruit = getItem(position);
        holder.TextViewName.setText(currentFruit.getName());
        holder.TextVewOrigin.setText(currentFruit.getOrigin());
        holder.ImagenViewIcon.setImageResource(currentFruit.getIcon());
        return convertView;
    }

    static class ViewHolder{
        private TextView TextViewName;
        private TextView TextVewOrigin;
        private ImageView ImagenViewIcon;
    }
}
