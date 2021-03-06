package com.example.yhisl.cityworld.models;

import android.widget.RatingBar;

import com.example.yhisl.cityworld.app.MyApplication;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by yhisl on 17/04/2017.
 */

public class City extends RealmObject {

    @PrimaryKey
    private int id;
    private String nombre;
    private String descripcion;
    private int imageBack;
    private float star;
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City(){

    }

    public City(String nombre, String descripcion, String link, float star){
        this.id = MyApplication.CityID.incrementAndGet(); //incrementa el id, con ayuda de método getIdByTable en MyApplication
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.link = link;
        this.imageBack = imageBack;
        this.star = star;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImageBack() {
        return imageBack;
    }

    public void setImageBack(int imageBack) {
        this.imageBack = imageBack;
    }

    public float getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
