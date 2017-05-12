package com.example.yhisl.cityworld.activities;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.yhisl.cityworld.R;
import com.example.yhisl.cityworld.models.City;
import com.squareup.picasso.Picasso;

import io.realm.Realm;

public class SecondActivity extends AppCompatActivity {

    private Realm realm;
    private City city;

    private int cityId;
    private boolean isCreation;

    private EditText name;
    private EditText description;
    private EditText link;
    private ImageView cityImage;
    private ImageButton btnPreview;
    private RatingBar rating;

    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_city);

        realm = Realm.getDefaultInstance();
        bindUIReferences();

        if (getIntent().getExtras() != null) {
            cityId = getIntent().getExtras().getInt("id");
            isCreation = false;
        } else {
            isCreation = true;
        }

        setActivityTitle();

        if (isCreation) {
            city = getCityById(cityId);
            bindDataToFields();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditCity();
            }
        });

        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textLink = link.getText().toString();
                if (textLink.length() > 0)
                    loadImageLinkForPreview(link.getText().toString());
            }
        });

    }

    private City getCityById(int cityId){
        return realm.where(City.class).equalTo("id", cityId).findFirst();
    }
    private void bindUIReferences(){


        name = (EditText) findViewById(R.id.editTextName);
        description = (EditText) findViewById(R.id.editTextDescription);
        link = (EditText) findViewById(R.id.editTextLink);
        rating = (RatingBar) findViewById(R.id.textRating);
        cityImage = (ImageView) findViewById(R.id.imageBackground);
        btnPreview = (ImageButton) findViewById(R.id.imageButtonView);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    private void setActivityTitle(){
        String title = "Edit City";
        if(isCreation) title = "Create New City";
        setTitle(title);
    }

    private void bindDataToFields(){
        name.setText(city.getNombre());
        description.setText(city.getDescripcion());
        link.setText(city.getLink());
        loadImageLinkForPreview(city.getLink());
        rating.setRating(city.getStar());
    }
    private void goToMainActivity(){

        Toast.makeText(this,"La ciudad ha sido creada exitosamente",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SecondActivity.this,MainActivity.class);
        startActivity(intent);
    }

    private void loadImageLinkForPreview(String link){
        Picasso.with(this).load(link).fit().into(cityImage);
    }

    private boolean isValidDataForNewCity(){
        if (name.getText().toString().length() > 0 &&
                description.getText().toString().length() > 0 &&
                link.getText().toString().length() > 0){
            return true;
        } else{
            return false;
        }
    }

    public void addEditCity() {
        if(isValidDataForNewCity()){
        String name_ = name.getText().toString().trim();
        String description_ = description.getText().toString().trim();
        String editLink = link.getText().toString();
        float stars = rating.getNumStars();

        City city = new City(name_, description_, editLink, stars);

        if (isCreation) city.setId(cityId);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(city);
        realm.commitTransaction();

        goToMainActivity();
    }
        else{
            Toast.makeText(this,"the data is not valid, please check the fields",Toast.LENGTH_LONG).show();

        }
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

