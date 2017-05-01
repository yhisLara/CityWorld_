package com.example.yhisl.cityworld.adapters;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder>{

    private Context context;
    private int layout;
    private List<City> cities;
    private OnItemClickListener itemClickListener;

    public CityAdapter (Context context, int layout , List<City> cities, OnItemClickListener itemClickListener){
        this.context = context;
        this.layout = layout;
        this.cities = cities;
        this.itemClickListener = itemClickListener;

    }


    public int getCount() {
        return cities.size();
    }


    public Object getItem(int position) {
        return cities.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent,false);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(cities.get(position), itemClickListener);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder vh ;
        ImageView imagenPoster;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout, null);
            vh = new ViewHolder(convertView);
            vh.name = (TextView) convertView.findViewById(R.id.textNameCity);
            vh.description = (TextView) convertView.findViewById(R.id.textDescription);
            vh.ranking = (TextView) convertView.findViewById(R.id.textRating);
            vh.imageBack = (ImageView) convertView.findViewById(R.id.imageBackground);

            convertView.setTag(vh);
        }
        else{

            vh = (ViewHolder) convertView.getTag();
        }

        City city = cities.get(position);
        vh.name.setText(city.getNombre());
        vh.description.setText(city.getDescripcion());
        vh.ranking.setText(city.getStar());

        return convertView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements OnItemClickListener {
        TextView name;
        TextView description;
        TextView ranking;
        ImageView imageBack;
        OnItemClickListener itemClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textNameCity);
            description = (TextView) itemView.findViewById(R.id.textDescription);
            ranking = (TextView) itemView.findViewById(R.id.textRating);
            imageBack = (ImageView) itemView.findViewById(R.id.imageBackground);

            //Agregar los onclick
        }

        public void bind(final City cities, final OnItemClickListener listener){
            name.setText(cities.getNombre());
            description.setText(cities.getDescripcion());
            ranking.setText(cities.getStar() + "");

            //Picasso.with(context).load(cities.).fit().into(imageBack);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(cities,getAdapterPosition());
                }
            });
        }

        @Override
        public void onItemClick(City cities, int position) {

        }
    }
    public interface OnItemClickListener{
        void onItemClick(City cities, int position);
    }
}
