package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 25-12-2016.
 */

import com.google.gson.annotations.SerializedName;


public class Episode {

    @SerializedName("still_path")
    private String posterPath;



    @SerializedName("air_date")
    private String airDate;

    @SerializedName("episode_number")
    private int epi;

    @SerializedName("overview")
    private String plot;

    @SerializedName("name")
    private String name;

    @SerializedName("vote_average")
    private double rating;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }



    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public int getEpi() {
        return epi;
    }

    public void setEpi(int ssn) {
        this.epi = ssn;
    }

    public void setName( String name ){
        this.name = name;

    }

    public String getName(){
        return name;
    }

    public void setplot( String plot){
        this.plot = plot;
    }

    public String getPlot(){
        return plot;
    }

    public void rating( double  rating){
        this.rating = rating;
    }

    public double getRating(){
        return rating;
    }



}
