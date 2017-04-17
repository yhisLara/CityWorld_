package com.example.yhisl.cityworld.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.yhisl.cityworld.models.City;

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
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
