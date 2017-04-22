package com.example.yhisl.cityworld;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;

import com.example.yhisl.cityworld.adapters.CityAdapter;
import com.example.yhisl.cityworld.models.City;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<City>>, AdapterView.OnItemClickListener {

    private Realm realm;
    private RealmResults<City> cities;

    private CityAdapter adapter;
    private ListView listView;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        realm = Realm.getDefaultInstance();
        cities = realm.where(City.class).findAll();
        cities.addChangeListener(this);
        adapter = new CityAdapter(this ,R.layout.add_city, cities);
        fab = (FloatingActionButton) findViewById(R.id.fabAddCity);
        listView = (ListView) findViewById(R.layout.activity_main);
        listView.setAdapter(adapter);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewCity();
            }
        });

    }

    private void addNewCity() {

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
        realm.commitTransaction();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onChange(RealmResults<City> element) {

    }
}
