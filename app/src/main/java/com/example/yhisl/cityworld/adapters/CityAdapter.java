package com.example.yhisl.cityworld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yhisl.cityworld.R;
import com.example.yhisl.cityworld.models.City;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by yhisl on 17/04/2017.
 */

public class CityAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<City> cities;

    public CityAdapter (Context context, int layout , List<City> cities){
        this.context = context;
        this.layout = layout;
        this.cities = cities;
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder vh ;
        ImageView imagenPoster;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder();
            vh.name = (TextView) convertView.findViewById(R.id.textNameCity);
            vh.description = (TextView) convertView.findViewById(R.id.textDescription);
            vh.ranking = (TextView) convertView.findViewById(R.id.textRating);
            vh.imageBack = (ImageView) convertView.findViewById(R.id.imageBackground);

            convertView.setTag(vh);
        }
        else{

            vh = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public class ViewHolder {
        TextView name;
        TextView description;
        TextView ranking;
        ImageView imageBack;
    }
}
