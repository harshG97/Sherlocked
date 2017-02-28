package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 25-12-2016.
 */

import com.google.gson.annotations.SerializedName;

public class Season {

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("season_number")
    private String ssn;

    public Season(String poster, String airDate, String ssn) {
        this.poster = poster;
        this.airDate = airDate;
        this.ssn = ssn;
    }

    public String getPosterPath() {
        return poster;
    }

    public void setPosterPath(String posterPath) {
        this.poster = poster;
    }

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }




}

