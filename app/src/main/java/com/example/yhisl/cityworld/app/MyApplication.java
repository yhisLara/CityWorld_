package com.example.yhisl.cityworld.app;

import android.app.Application;

import com.example.yhisl.cityworld.models.City;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by yhisl on 18/04/2017.
 */

public class MyApplication extends Application {

    public static AtomicInteger CityID = new AtomicInteger();

    @Override
    public void onCreate() {

        realmConfig();
        Realm realm = Realm.getDefaultInstance();
        CityID = getIdByTable(realm, City.class);
        realm.close();

    }

    private void realmConfig(){
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    //Clase generica, busca utlimo ID cualquier clase
    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }
}
