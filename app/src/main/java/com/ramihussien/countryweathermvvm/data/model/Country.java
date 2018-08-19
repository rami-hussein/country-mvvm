package com.ramihussien.countryweathermvvm.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@SuppressWarnings("NullableProblems")
@Entity(tableName = "countries")
public class Country implements Serializable {

    @SerializedName("name")
    private String name;

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "alpha2_code")
    @SerializedName("alpha2Code")
    private String alpha2Code;

    @ColumnInfo(name = "alpha3_code")
    @SerializedName("alpha3Code")
    private String alpha3Code;

    @SerializedName("capital")
    private String capital;

    @SerializedName("region")
    private String region;

    @SerializedName("population")
    private Integer population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getFlagUrl() {
        return "http://www.geognos.com/api/en/countries/flag/" + alpha2Code + ".png";
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name)
                .append("alpha2Code", alpha2Code)
                .append("alpha3Code", alpha3Code)
                .append("capital", capital)
                .append("region", region)
                .append("population", population)
                .toString();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + alpha2Code.hashCode();
        result = 31 * result + alpha3Code.hashCode();
        result = 31 * result + capital.hashCode();
        result = 31 * result + region.hashCode();
        result = 31 * result + population;
        return result;
    }
}
