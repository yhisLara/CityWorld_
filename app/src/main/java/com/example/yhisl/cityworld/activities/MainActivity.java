package com.example.yhisl.cityworld.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.Toast;

import com.example.yhisl.cityworld.R;
import com.example.yhisl.cityworld.adapters.CityAdapter;
import com.example.yhisl.cityworld.models.City;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements RealmChangeListener<RealmResults<City>> {

    private Realm realm;
    private RealmResults<City> cities;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayout;
    private CityAdapter adapter;

    private FloatingActionButton fab;
    final int ACTION_NUMBER_ADD = 1;
    final int ACTION_NUMBER_EDIT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();
        cities = realm.where(City.class).findAll();
        cities.addChangeListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayout = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayout);

        fab = (FloatingActionButton) findViewById(R.id.fabAddCity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });


        adapter = new CityAdapter(this, R.layout.cardview_items, cities, new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(City cities, int position) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("id", cities.getId());
                startActivity(intent);
            }
        }, new CityAdapter.OnButtonClickListener() {
            @Override
            public void onButtonClick(City city, int position) {
                showAlertForRemovingCity("Delete City", " Are you sure you want to delete" + city.getNombre()+ "?", position);
            }
        });

        mRecyclerView.setAdapter(adapter);
        cities.addChangeListener(this);

        setHideShowFAB();

    }

    private void setHideShowFAB(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                if(dy > 0)
                    fab.hide();
                else if(dy < 0)
                    fab.show();
            }
        });
    }

    private void showAlertForRemovingCity(String title, String message, final int position){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Remove", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteCity(position);
                        Toast.makeText(MainActivity.this, "It has been deleted successfully",Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", null).show();
    }

    private void deleteCity(int position){
        realm.beginTransaction();
        cities.get(position).deleteFromRealm();
        realm.commitTransaction();
    }

    public void onChange(RealmResults<City> cities) {
        adapter.notifyDataSetChanged();
    }
}
