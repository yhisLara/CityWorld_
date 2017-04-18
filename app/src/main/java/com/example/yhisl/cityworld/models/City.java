package com.example.yhisl.cityworld.models;

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
    @Required
    private String nombre;
    @Required
    private String descripcion;
    @Required
    private int imageBack;
    @Required
    private int star;

    public City(){

    }

    public City(String nombre, String descripcion, int imageBack, int star){
        this.id = MyApplication.
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
