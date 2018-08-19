package com.ramihussien.countryweathermvvm.data.local.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ramihussien.countryweathermvvm.data.model.Country;

import java.util.List;

@Dao
public interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Country country);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Country> countries);

    @Query("SELECT * FROM countries")
    LiveData<List<Country>> loadAll();

}
