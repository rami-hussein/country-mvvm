package com.ramihussien.countryweathermvvm.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.ramihussien.countryweathermvvm.data.local.db.dao.CountryDao;
import com.ramihussien.countryweathermvvm.data.model.Country;


@Database(entities = {Country.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CountryDao countryDao();
}
