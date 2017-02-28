package com.example.harsh.sherlocked.extra;

/**
 * Created by Harsh on 25-12-2016.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Harsh on 24-12-2016.
 */

public class EpisodeResponse {

    @SerializedName("episodes")
    private List<Episode> results;

    @SerializedName("season_number")
    private int ssn;

    @SerializedName("air_date")
    private String airDate;

    @SerializedName("poster_path")
    private String poster;


    public List<Episode> getResults() {
        return results;
    }

    public void setResults(List<Episode> results) {
        this.results = results;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getDate() {
        return airDate;
    }

    public void setDate(String airDate) {  this.airDate = airDate;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }





}
