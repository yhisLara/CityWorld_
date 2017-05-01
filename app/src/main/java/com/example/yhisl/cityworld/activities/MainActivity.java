package com.example.yhisl.cityworld.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.yhisl.cityworld.R;
import com.example.yhisl.cityworld.adapters.CityAdapter;
import com.example.yhisl.cityworld.models.City;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<City>>, AdapterView.OnItemClickListener {

    private Realm realm;
    private RealmResults<City> cities;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayout;
    private RecyclerView.Adapter adapter;

    private RecyclerView listView;
    private FloatingActionButton fab;
    final int ACTION_NUMBER_ADD = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        realm = Realm.getDefaultInstance();
        cities = realm.where(City.class).findAll();
        cities.addChangeListener(this);
        fab = (FloatingActionButton) findViewById(R.id.fabAddCity);
        adapter = new CityAdapter(this ,R.layout.cardview_items, cities, new CityAdapter.OnItemClickListener(){

            @Override
            public void onItemClick(City cities, int position) {

            }
        });
        listView = (RecyclerView) findViewById(R.id.recyclerView);
        listView.setAdapter(adapter);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayout);
        mRecyclerView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCity();

            }
        });


    }

    private void addNewCity() {

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra("ACTION",ACTION_NUMBER_ADD);
        startActivity(intent);
        /*
        View view = LayoutInflater.from(this).inflate(R.layout.add_city,null);
        EditText name = (EditText) view.findViewById(R.id.editTextName);
        EditText description = (EditText) view.findViewById(R.id.editTextDescription);
        EditText link = (EditText) view.findViewById(R.id.editTextLink);
        RatingBar rating = (RatingBar) view.findViewById(R.id.textRating);

        String name_ = name.getText().toString().trim();
        String description_ = description.getText().toString().trim();
        int rating_ = rating.getNumStars();
        realm.beginTransaction();
        City city = new City(name_,description_,rating_);
        realm.copyToRealm(city);
        realm.commitTransaction();*/

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public void onChange(RealmResults<City> cities) {
        adapter.notifyDataSetChanged();
    }
}
