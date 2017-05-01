package com.example.yhisl.cityworld.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.yhisl.cityworld.R;
import com.example.yhisl.cityworld.models.City;

import io.realm.Realm;

public class SecondActivity extends AppCompatActivity {

    private Realm realm;

    private EditText name;
    private EditText description;
    private EditText link;
    private RatingBar rating;

    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);

        realm = Realm.getDefaultInstance();

        Bundle action = this.getIntent().getExtras();
        int action_number = action.getInt("ACTION");

        name = (EditText) findViewById(R.id.editTextName);
        description = (EditText) findViewById(R.id.editTextDescription);
        link = (EditText) findViewById(R.id.editTextLink);
        rating = (RatingBar) findViewById(R.id.textRating);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCity();
            }
        });
    }

    public void addCity() {

        String name_ = name.getText().toString().trim();
        String description_ = description.getText().toString().trim();
        int rating_ = rating.getNumStars();

        realm.beginTransaction();
        City city = new City(name_,description_,rating_);
        realm.copyToRealm(city);
        realm.commitTransaction();

        Toast.makeText(this,"La ciudad ha sido creada exitosamente",Toast.LENGTH_LONG).show();

        Intent intent = new Intent(SecondActivity.this,MainActivity.class);
        startActivity(intent);
    }

      /*  if(action_number == 1){

            String name_ = name.getText().toString().trim();
            String description_ = description.getText().toString().trim();
            int rating_ = rating.getNumStars();

            addCity(name_,description_,rating_);
        }
        else{
            editCity();
        }
    }

    public void addCity(String name, String description, int rating) {

         String name_ = name.getText().toString().trim();
            String description_ = description.getText().toString().trim();
            int rating_ = rating.getNumStars();

            addCity(name_,description_,rating_);
        realm.beginTransaction();
        City city = new City(name,description,rating);
        realm.copyToRealm(city);
        realm.commitTransaction();
    }

    private  void editCity(){*/

    }

